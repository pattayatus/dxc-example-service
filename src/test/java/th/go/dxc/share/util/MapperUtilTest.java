package th.go.dxc.share.util;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;

class MapperUtilTest {
	private final MapperUtil mapperUtil;

	
	public MapperUtilTest() {
		super();
		ObjectMapper objectMapper = new ObjectMapper();
		MapperFactory mapperFactory = new DefaultMapperFactory.Builder().build();
		MapperFacade mapperFacade = mapperFactory.getMapperFacade();
		this.mapperUtil = new MapperUtil(objectMapper, mapperFacade);
		
	}


	@Test
	void testMapPathVariableList_All_matched_path_variables_are_found() {
		final String uriPath = "/api/v2/moi-linkage/{test1}/persons/{test2}/moi-dopa-persons/{test3}";
		List<String>  pathVariableList = mapperUtil.mapPathVariableList(uriPath);
		assertThat(pathVariableList).hasSize(3)
		.containsSequence("test1","test2","test3");
	}
	@Test
	void testMapUriPath_All_path_variables_are_replaced_single_value() {
		final String uriPath = "/api/v2/moi-linkage/{test1}/persons/{test2}/moi-dopa-persons/{test3}";
		final String expectedUriPath = "/api/v2/moi-linkage/value1/persons/value2/moi-dopa-persons/value3";
		MultiValueMap<String,String> parameterMap = new LinkedMultiValueMap<String, String> ();
		parameterMap.add("test1", "value1");
		parameterMap.add("test2", "value2");
		parameterMap.add("test3", "value3");
		String mappedUriPath = mapperUtil.mapUriPath(uriPath, parameterMap);
		assertThat(mappedUriPath).isEqualTo(expectedUriPath);
	}
	
	@Test
	void testMapCamelCaseFromKebabCase_three_words_kebab_case_text_map_into_camel_case() {
		final String kebabCase = "moi-dopa-person";
		final String expected = "moiDopaPerson";
		String camelCase = mapperUtil.mapCamelCaseFromKebabCase(kebabCase);
		assertThat(camelCase).isEqualTo(expected);
	}

}
