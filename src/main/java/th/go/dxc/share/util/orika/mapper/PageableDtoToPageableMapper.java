package th.go.dxc.share.util.orika.mapper;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import th.go.dxc.share.model.BasicPageRequest;
import th.go.dxc.share.model.PageableDto;

public class PageableDtoToPageableMapper extends CustomMapper<PageableDto, Pageable>{

	@Override
	public void mapAtoB(PageableDto a, Pageable b, MappingContext context) {
		BasicPageRequest bc = (BasicPageRequest)b;
		if(a!=null)
		{
		bc.setPageNumber(a.getPage());
		bc.setPageSize(a.getSize());
		List<String> sortProperties = a.getSortProperties();
		if(sortProperties!=null)
		{
			bc.setSort(Sort.by(a.getSortDirection(),a.getSortProperties().toArray(new String[0])));
		}
		}
	}

	
	
}
