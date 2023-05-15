package th.go.dxc.share.util.orika.converter;

import java.util.HashMap;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import th.go.dxc.share.model.PersonSexCodeSimpleType;

@Slf4j
public class PersonSexCodeSimpleTypeStringConverter extends BidirectionalConverter<PersonSexCodeSimpleType, String>{

	private final Map<String, PersonSexCodeSimpleType> stringSexTypeMap = new HashMap<String, PersonSexCodeSimpleType>();
	private final Map<PersonSexCodeSimpleType,String> sexTypeStringMap = new HashMap<PersonSexCodeSimpleType,String>();

	
	
	public PersonSexCodeSimpleTypeStringConverter(String femaleText,String maleText,String nonBinaryText,String unknownText) {
		super();
		stringSexTypeMap.put(femaleText, PersonSexCodeSimpleType.FEMALE);
		stringSexTypeMap.put(maleText, PersonSexCodeSimpleType.MALE);
		stringSexTypeMap.put(nonBinaryText, PersonSexCodeSimpleType.NON_BINARY);
		stringSexTypeMap.put(unknownText, PersonSexCodeSimpleType.UNKNOWN);
		sexTypeStringMap.put( PersonSexCodeSimpleType.FEMALE,femaleText);
		sexTypeStringMap.put(PersonSexCodeSimpleType.MALE,maleText);
		sexTypeStringMap.put(PersonSexCodeSimpleType.NON_BINARY,nonBinaryText);
		sexTypeStringMap.put(PersonSexCodeSimpleType.UNKNOWN,unknownText);
	}

	@Override
	public String convertTo(PersonSexCodeSimpleType source, Type<String> destinationType, MappingContext mappingContext) {
		String sexText = sexTypeStringMap.get(source);
		log.trace("sexText: "+source+"->"+sexText);
		return sexText;
	}

	@Override
	public PersonSexCodeSimpleType convertFrom(String source, Type<PersonSexCodeSimpleType> destinationType, MappingContext mappingContext) {
		PersonSexCodeSimpleType sexType = stringSexTypeMap.get(source);
		log.trace("sexType: "+source+"->"+sexType);
		
		if(sexType==null)sexType = PersonSexCodeSimpleType.UNKNOWN;
		return sexType;
	}

}
