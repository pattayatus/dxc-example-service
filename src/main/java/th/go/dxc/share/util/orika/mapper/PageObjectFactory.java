package th.go.dxc.share.util.orika.mapper;

import java.util.ArrayList;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.ObjectFactory;

public class PageObjectFactory implements ObjectFactory<Page<?>>{

	@Override
	public Page<?> create(Object source, MappingContext mappingContext) {
		Page<?> pageResult = null;
		if(source instanceof Page) {
			Page<?> pageSource = (Page<?>)source;
			pageResult = new PageImpl<>(pageSource.getContent(),pageSource.getPageable(), pageSource.getTotalElements());
		}else
		{
			pageResult = new PageImpl<>(new ArrayList<>());
		}
		return pageResult;
	}

}
