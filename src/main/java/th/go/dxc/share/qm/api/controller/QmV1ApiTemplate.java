package th.go.dxc.share.qm.api.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import lombok.extern.slf4j.Slf4j;
import th.go.dxc.share.exception.BadGatewayException;
import th.go.dxc.share.exception.BadRequestException;
import th.go.dxc.share.exception.InternalServerErrorException;
import th.go.dxc.share.exception.NotFoundException;
import th.go.dxc.share.qm.api.dto.QmV1QueryParameterDto;
import th.go.dxc.share.qm.api.dto.QmV1ResultDto;
import th.go.dxc.share.qm.api.dto.QmV1SecurityParameterDto;
import th.go.dxc.share.qm.api.dto.QmV1Transaction;
import th.go.dxc.share.qm.api.util.QmV1ApiMapper;
import th.go.dxc.share.qm.app.model.QmModel;
import th.go.dxc.share.qm.app.service.QmService;
@Slf4j
public class QmV1ApiTemplate<MODEL extends QmModel,  MODEL_FILTER,RESULT_DTO extends QmV1ResultDto<MODEL>> implements QmV1Api<MODEL>{
	
	private final QmService<MODEL, MODEL_FILTER> service;
	private final QmV1ApiMapper<MODEL, MODEL_FILTER> mapper;
	private final Class<RESULT_DTO> resultDtoClass;
	public QmV1ApiTemplate(QmService<MODEL,  MODEL_FILTER> service, QmV1ApiMapper<MODEL, MODEL_FILTER> mapper,Class<RESULT_DTO> resultDtoClass) {
		super();
		this.service = service;
		this.mapper = mapper;
		this.resultDtoClass = resultDtoClass;
	}

	@Override
	public RESULT_DTO findByCriteria(@Valid QmV1SecurityParameterDto securityParameterDto,
			@Valid QmV1QueryParameterDto queryParameterDto, HttpServletRequest request) throws BadRequestException, BadGatewayException, InternalServerErrorException {
		log.debug("findByCriteria: "+queryParameterDto+" - "+securityParameterDto);
		QmV1Transaction transaction = new QmV1Transaction();
		transaction.begin();
		// validate values
		if(queryParameterDto==null)queryParameterDto = new QmV1QueryParameterDto();
		// map values
		RESULT_DTO resultDto = null;
		String criteria = mapper.mapCriteria(queryParameterDto);
		Pageable pageable = mapper.mapPageable(queryParameterDto);
		
		Page<MODEL> appPage = service.findByCriteria(criteria, pageable);
		
		transaction.end();
		try {
			resultDto = mapper.mapResultDto(queryParameterDto, securityParameterDto, request, transaction, appPage,resultDtoClass);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new InternalServerErrorException("Error while creating result. "+e.getMessage(),e);
		}
		return resultDto;
	}

	@Override
	public RESULT_DTO getById(String id, HttpServletRequest request,
			@Valid QmV1SecurityParameterDto securityParamDto) throws BadRequestException, BadGatewayException, InternalServerErrorException {
		QmV1Transaction transaction = new QmV1Transaction();
		transaction.begin();
		RESULT_DTO resultDto = null;
		Optional<MODEL> appDomain = Optional.empty();		
		try {
			appDomain = service.findById(id);
		} catch (NotFoundException e) {
			log.debug(e.getMessage());
		}
		transaction.end();
		try {
			resultDto = mapper.mapResultDto(id, securityParamDto, request, transaction, appDomain,resultDtoClass);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			throw new InternalServerErrorException("Error while creating result. "+e.getMessage(),e);
		}
		return resultDto;
	}



}
