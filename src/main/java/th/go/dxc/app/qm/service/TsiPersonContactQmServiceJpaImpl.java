package th.go.dxc.app.qm.service;

import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.sf.jsqlparser.JSQLParserException;
import th.go.dxc.app.qm.model.TsiPersonContact;
import th.go.dxc.app.qm.model.TsiPersonContactFilter;
import th.go.dxc.app.qm.util.TsiPersonContactOrikaMapperFactoryConfigurer;
import th.go.dxc.app.qm.util.TsiPersonContactQmServiceJpaImplMapper;
import th.go.dxc.infra.datasource.data_db.entity.TsiPersonContactEntity;
import th.go.dxc.infra.datasource.data_db.repository.TsiPersonContactRepository;
import th.go.dxc.share.exception.BadGatewayException;
import th.go.dxc.share.exception.BadRequestException;
import th.go.dxc.share.exception.InternalServerErrorException;
import th.go.dxc.share.exception.NotFoundException;
import th.go.dxc.share.qm.app.service.QmService;

public class TsiPersonContactQmServiceJpaImpl implements QmService<TsiPersonContact,TsiPersonContactFilter>{

	private final TsiPersonContactQmServiceJpaImplMapper mapper;
	private final TsiPersonContactRepository repository;

	public TsiPersonContactQmServiceJpaImpl(TsiPersonContactQmServiceJpaImplMapper mapper, TsiPersonContactRepository repository) {
		super();
		this.mapper = mapper;
		this.repository = repository;
	}

	@Override
	public Page<TsiPersonContact> findAll(TsiPersonContactFilter filter, Pageable pageable)
			throws BadRequestException, BadGatewayException, InternalServerErrorException {
		Page<TsiPersonContact> resultPage = null;
		Pageable entityPageable = null;
		if(pageable==null)
		{
			entityPageable = TsiPersonContactQmServiceJpaImplMapper.DEFAULT_PAGEABLE;
		}else
		{
			entityPageable = mapper.mapPageable(pageable, TsiPersonContactOrikaMapperFactoryConfigurer.PAGEABLE_DIF_PROPERTY_MAP);			
		}
		if(filter==null)
		{
			Page<TsiPersonContactEntity> entityPage = repository.findAll(entityPageable);
			resultPage = mapper.mapPageDto(entityPage, TsiPersonContact.class);			
		}else
		{
			Example<TsiPersonContactEntity> example = mapper.mapExample(filter, TsiPersonContactEntity.class, true);
			Page<TsiPersonContactEntity> entityPage = repository.findAll(example,entityPageable);
			resultPage = mapper.mapPageDto(entityPage, TsiPersonContact.class);
		}
		return resultPage;
	}

	@Override
	public Page<TsiPersonContact> findByCriteria(String criteria, Pageable pageable)
			throws BadRequestException, BadGatewayException, InternalServerErrorException {
		Page<TsiPersonContact> resultPage = null;
		try {
			TsiPersonContactFilter filter = mapper.mapByCriteria(criteria,TsiPersonContactFilter.class);
			resultPage = this.findAll(filter, pageable);
		} catch (JSQLParserException e) {
			throw new BadRequestException("Invalid criteria="+criteria);
		}
		return resultPage;
	}

	@Override
	public Optional<TsiPersonContact> findById(String id)
			throws NotFoundException, BadRequestException, BadGatewayException, InternalServerErrorException {
		Optional<TsiPersonContact> result = Optional.empty();
		if(id==null)throw new BadRequestException("Invalid Id");
		try {
			Long entityId = Long.valueOf(id);
			Optional<TsiPersonContactEntity> entity= repository.findById(entityId);
			result = mapper.mapOptional(entity, TsiPersonContact.class);
		}catch(NumberFormatException nex) {
			throw new BadRequestException("Invalid Id="+id);
		}
		return result;
	}


}
