package th.go.dxc.share.util.orika.converter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import th.go.dxc.share.model.PageableDto;

public class PageableDtoToPageConverter extends BidirectionalConverter<PageableDto, Pageable>{

	@Override
	public Pageable convertTo(PageableDto source, Type<Pageable> destinationType, MappingContext mappingContext) {
		Pageable pageable = null;
		if(source!=null)
		{
			Integer page = source.getPage();
			Integer size = source.getSize();
			List<String> sortProperties = source.getSortProperties();
			Direction sortDirection = source.getSortDirection();
			if(page==null || page <0)page = 0;
			if(size==null||size<1)size = 10;
			if(sortDirection==null)sortDirection = Direction.ASC;
			if(sortProperties == null || sortProperties.size()==0)
			{
				pageable = PageRequest.of(page, size);
			}else
			{
				pageable = PageRequest.of(page, size, sortDirection, sortProperties.toArray(new String[] {}));
			}
		}
		return pageable;
	}

	@Override
	public PageableDto convertFrom(Pageable source, Type<PageableDto> destinationType, MappingContext mappingContext) {
		PageableDto target = null;
		if(source!=null) {
			Sort sort = source.getSort();
			List<Order> orderList = sort.toList();
			List<String> propertyList = new ArrayList<String>();
			Direction direciton = Direction.ASC;
			for (Iterator<Order> iterator = orderList.iterator(); iterator.hasNext();) {
				Order order = iterator.next();
				propertyList.add(order.getProperty());
				direciton = order.getDirection();
			}			
			target = new PageableDto(source.getPageNumber(), source.getPageSize(), propertyList, direciton);
		}
		return target;
	}

}
