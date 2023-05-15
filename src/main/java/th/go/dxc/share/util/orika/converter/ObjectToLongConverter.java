package th.go.dxc.share.util.orika.converter;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;

public class ObjectToLongConverter extends CustomConverter<Object, Long>{

	@Override
	public Long convert(Object source, Type<? extends Long> destinationType, MappingContext mappingContext) {
		Long destination = null;
		if(source!=null)
		{
			if(source instanceof Long)
			{
				destination = (Long)source;
			}else {
				destination = Long.valueOf(source.toString());
			}
		}
		
		return destination;
	}




}
