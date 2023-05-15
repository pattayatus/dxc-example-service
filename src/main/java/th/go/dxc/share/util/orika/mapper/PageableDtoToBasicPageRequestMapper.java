package th.go.dxc.share.util.orika.mapper;

import java.util.List;

import org.springframework.data.domain.Sort;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import th.go.dxc.share.model.BasicPageRequest;
import th.go.dxc.share.model.PageableDto;

public class PageableDtoToBasicPageRequestMapper extends CustomMapper<PageableDto, BasicPageRequest>{

	@Override
	public void mapAtoB(PageableDto a, BasicPageRequest b, MappingContext context) {
		if(a!=null)
		{
		b.setPageNumber(a.getPage());
		b.setPageSize(a.getSize());
		List<String> sortProperties = a.getSortProperties();
		if(sortProperties!=null)
		{
			b.setSort(Sort.by(a.getSortDirection(),a.getSortProperties().toArray(new String[0])));
		}
		}
		super.mapAtoB(a, b, context);
	}

	
}
