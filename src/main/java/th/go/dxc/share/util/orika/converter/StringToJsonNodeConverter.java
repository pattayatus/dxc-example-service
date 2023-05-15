package th.go.dxc.share.util.orika.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class StringToJsonNodeConverter extends BidirectionalConverter<String, JsonNode> {
	private final ObjectMapper objectMapper;

	
	public StringToJsonNodeConverter(ObjectMapper objectMapper) {
		super();
		this.objectMapper = objectMapper;
	}

	@Override
	public JsonNode convertTo(String source, Type<JsonNode> destinationType, MappingContext mappingContext) {		
		JsonNode target = null;
		try {
			target = objectMapper.readTree(source);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return target;
	}

	@Override
	public String convertFrom(JsonNode source, Type<String> destinationType, MappingContext mappingContext) {
		return (source==null?null:source.asText());
	}
	
	
}