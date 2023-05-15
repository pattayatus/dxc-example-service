package th.go.dxc.share.util.orika.mapper;

import org.springframework.data.domain.Page;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;

public class PageToPageDtoMapper extends CustomMapper<Page<?>, Page<?>>{

	@Override
	public void mapAtoB(Page<?> a, Page<?> b, MappingContext context) {
	}


	
}
