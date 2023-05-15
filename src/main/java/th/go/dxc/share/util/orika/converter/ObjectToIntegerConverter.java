package th.go.dxc.share.util.orika.converter;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class ObjectToIntegerConverter extends CustomConverter<Object, Integer>{

	@Override
	public Integer convert(Object source, Type<? extends Integer> destinationType, MappingContext mappingContext) {
		Integer destination = null;
		if(source!=null)
		{
			if(source instanceof Long)
			{
				destination = (Integer)source;
			}else {
				destination = Integer.valueOf(source.toString());
			}
		}
		
		return destination;
	}




}
