package th.go.dxc.share.util.orika.converter;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import th.go.dxc.share.model.PageDto;

public class PageDtoConverter<DTO,DOMAIN>  extends BidirectionalConverter<Page<DTO>, Page<DOMAIN>>{

	private final MapperFacade mapper;
	private final Class<DTO> dtoClass;
	private final Class<DOMAIN> domainClass;
	public PageDtoConverter(MapperFacade mapper,Class<DTO> dtoClass,Class<DOMAIN> domainClass) {
		super();
		this.mapper = mapper;
		this.dtoClass = dtoClass;
		this.domainClass = domainClass;
	}

	@Override
	public Page<DOMAIN> convertTo(Page<DTO> source, Type<Page<DOMAIN>> destinationType,
			MappingContext mappingContext) {
		Page<DOMAIN> target = null;
		if(source!=null)
		{
			List<DOMAIN> domainList = mapper.mapAsList(source.getContent(), domainClass);
			target = new PageImpl<DOMAIN>(domainList, source.getPageable(), source.getTotalElements());
		}
		return target;
	}

	@Override
	public Page<DTO> convertFrom(Page<DOMAIN> source, Type<Page<DTO>> destinationType,
			MappingContext mappingContext) {
		Page<DTO> target = null;
		List<DTO> content;
		Pageable pageable;
		Long total;
		content = mapper.mapAsList(source.getContent(), dtoClass);
		pageable = source.getPageable();
		total = source.getTotalElements();
		
		target = new PageDto<DTO>(content, pageable, total); 
		return target;
	}

}
