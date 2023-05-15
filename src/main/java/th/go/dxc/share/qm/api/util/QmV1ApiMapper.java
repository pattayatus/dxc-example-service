package th.go.dxc.share.qm.api.util;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import th.go.dxc.share.model.OffsetBasedPageRequest;
import th.go.dxc.share.qm.api.dto.QmV1QueryParameterDto;
import th.go.dxc.share.qm.api.dto.QmV1ResultDto;
import th.go.dxc.share.qm.api.dto.QmV1ResultHeaderDto;
import th.go.dxc.share.qm.api.dto.QmV1SecurityParameterDto;
import th.go.dxc.share.qm.api.dto.QmV1Transaction;
import th.go.dxc.share.util.MapperUtil;

@Slf4j
public class QmV1ApiMapper<DOMAIN, DOMAIN_FILTER> extends MapperUtil {

	public QmV1ApiMapper(ObjectMapper objectMapper, MapperFacade mapper) {
		super(objectMapper, mapper);
	}

	public String mapCriteria(QmV1QueryParameterDto queryParameter) {
		String criteria = queryParameter != null ? queryParameter.getCriteria() : null;
		return criteria;
	}

	public Pageable mapPageable(QmV1QueryParameterDto queryParameter) {
		Pageable pageable = PageRequest.of(0, 100);
		if (queryParameter != null) {

			Integer offset = queryParameter.getOffset();
			Integer limit = queryParameter.getMaxNumberOfResults();
			Sort sort = mapAppSort(queryParameter.getOrderBy());
			if (sort != null) {
				pageable = new OffsetBasedPageRequest(offset, limit, sort);
			} else {
				pageable = new OffsetBasedPageRequest(offset, limit);
			}
		}
		return pageable;
	}

	public Sort mapAppSort(String orderBy) {
		Sort sort = null;
		String[] properties = null;
		Direction direciton = Direction.ASC;
		if (orderBy != null) {
			orderBy = orderBy.trim();
			if (orderBy.endsWith(Direction.ASC.name())) {
				direciton = Direction.ASC;
				orderBy = orderBy.substring(0, orderBy.length() - 3);
				orderBy = orderBy.trim();
			} else if (orderBy.endsWith(Direction.DESC.name())) {
				direciton = Direction.DESC;
				orderBy = orderBy.substring(0, orderBy.length() - 3);
				orderBy = orderBy.trim();
			}

			if (orderBy.contains(",")) {
				properties = orderBy.split(",");
				for (int i = 0; i < properties.length; i++) {
					String string = properties[i];
					properties[i] = string.trim();
				}
			} else {
				properties = new String[] { orderBy };
			}
			if (properties != null) {
				sort = Sort.by(direciton, properties);
			}
		}
		return sort;
	}

	public QmV1ResultHeaderDto mapResultHeaderDto(QmV1QueryParameterDto query, QmV1SecurityParameterDto security,
			HttpServletRequest request, Page<DOMAIN> appDomainPage, QmV1Transaction transaction) {
		log.debug("mapResultHeaderDto: query=" + query);
		QmV1ResultHeaderDto header = new QmV1ResultHeaderDto();

		header.setCriteria(query.getCriteria());
		header.setMaxNumberOfResults(query.getMaxNumberOfResults());
		header.setNumberOfResults(appDomainPage.getNumberOfElements());
		header.setOffset(query.getOffset());
		header.setOrderBy(query.getOrderBy());
		header.setRequesterAccessDateTime(transaction.getBeginTime());
		header.setRequesterAccessResource(request.getRequestURI());
		header.setRequesterGlobalTransactionId(security.getRequesterGlobalTransactionId());
		header.setRequesterIpAddress(request.getRemoteAddr());
		header.setRequesterLocalTransactionId(security.getRequesterLocalTransactionId());
		header.setRequesterToken(security.getRequesterToken());
		header.setRequesterUsername(security.getUserCitizenNumber());
		header.setResponderResponseDateTime(transaction.getEndTime());
		header.setResponderTransactionId(transaction.getId());
		header.setTotalNumberOfResults(appDomainPage.getTotalElements());
		return header;
	}

	public QmV1ResultHeaderDto mapResultHeaderDto(String dtoId, QmV1SecurityParameterDto security,
			HttpServletRequest request, DOMAIN appDomain, QmV1Transaction transaction) {
		log.debug("mapResultHeaderDto: dtoId=" + dtoId);
		QmV1ResultHeaderDto header = new QmV1ResultHeaderDto();

		header.setCriteria("id=" + dtoId);
		header.setMaxNumberOfResults(1);
		header.setNumberOfResults(appDomain == null ? 0 : 1);
		header.setOffset(0);
		header.setOrderBy(null);
		header.setRequesterAccessDateTime(transaction.getBeginTime());
		header.setRequesterAccessResource(request.getRequestURI());
		header.setRequesterGlobalTransactionId(security.getRequesterGlobalTransactionId());
		header.setRequesterIpAddress(request.getRemoteAddr());
		header.setRequesterLocalTransactionId(security.getRequesterLocalTransactionId());
		header.setRequesterToken(security.getRequesterToken());
		header.setRequesterUsername(security.getUserCitizenNumber());
		header.setResponderResponseDateTime(transaction.getEndTime());
		header.setResponderTransactionId(transaction.getId());
		header.setTotalNumberOfResults(appDomain == null ? 0L : 1L);
		return header;
	}

	public <T extends QmV1ResultDto<DOMAIN>> T mapResultDto(QmV1QueryParameterDto query,
			QmV1SecurityParameterDto security, HttpServletRequest request, QmV1Transaction transaction,
			Page<DOMAIN> appDomainPage, Class<T> resultDtoClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		T resultDto = null;
		resultDto = resultDtoClass.getDeclaredConstructor().newInstance();
		QmV1ResultHeaderDto header = this.mapResultHeaderDto(query, security, request, appDomainPage, transaction);
		List<DOMAIN> data = appDomainPage.getContent();
		resultDto.setHeader(header);
		resultDto.setData(data);
		return resultDto;
	}

	public <T extends QmV1ResultDto<DOMAIN>> T mapResultDto(String id, QmV1SecurityParameterDto security, HttpServletRequest request,
			QmV1Transaction transaction, Optional<DOMAIN> model,Class<T> resultDtoClass) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		T resultDto = null;
		resultDto = resultDtoClass.getDeclaredConstructor().newInstance();
		QmV1ResultHeaderDto header = this.mapResultHeaderDto(id, security, request,
				model.isPresent() ? model.get() : null, transaction);
		List<DOMAIN> data = (model.isPresent() ? Collections.singletonList(model.get()) : new ArrayList<DOMAIN>());
		resultDto.setHeader(header);
		resultDto.setData(data);
		return resultDto;
	}
}
