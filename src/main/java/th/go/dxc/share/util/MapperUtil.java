package th.go.dxc.share.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeBuilder;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.operators.relational.ComparisonOperator;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import th.go.dxc.share.exception.BadRequestException;
import th.go.dxc.share.model.PageDto;
import th.go.dxc.share.model.PageableDto;
import th.go.dxc.share.util.comparator.BeanComparator;

@Slf4j
@Component
public class MapperUtil {
	public final static Pageable DEFAULT_PAGEABLE = Pageable.ofSize(100);
	private static final String ID_DELIMITER = "~";
	public final static String QUERY_PARAM_SET_DELIM = "&";
	public final static String QUERY_PARAM_KEY_VALUE_DELIM = "=";
	public final static Type<Map<String, String>> MAP_STRING_STRING_TYPE = new TypeBuilder<Map<String, String>>() {
	}.build();
	public final static ExampleMatcher KEYWORD_MATCHER = ExampleMatcher.matchingAll().withIgnoreCase()
			.withStringMatcher(StringMatcher.CONTAINING);

	protected final ObjectMapper objectMapper;
	protected final MapperFacade mapper;

	public MapperUtil(ObjectMapper objectMapper, MapperFacade mapper) {
		super();
		this.objectMapper = objectMapper;
		this.mapper = mapper;
	}

	public <TARGET> List<TARGET> sortAndSlice(List<TARGET> modelList, Pageable pageable, Class<TARGET> modelClass) {
		List<TARGET> processedList = new ArrayList<TARGET>(modelList);
		if (pageable != null) {
			List<Order> orderList = pageable.getSort().toList();
			processedList.sort(new BeanComparator<TARGET>(modelClass, orderList));
			Long startIndex = pageable.getOffset();
			Long endIndex = startIndex + pageable.getPageSize();
			if (endIndex > modelList.size())
				endIndex = Long.valueOf(modelList.size());
			processedList = processedList.subList(startIndex.intValue(), endIndex.intValue());
		}
		return processedList;
	}

	public <TARGET> List<TARGET> filter(List<TARGET> modelList, TARGET filter, Type<TARGET> modelType,
			boolean isExactMatch) {
		List<TARGET> resultList = null;
		if (modelList != null && modelList.size() > 0) {
			resultList = modelList.stream().filter(t -> isMatchFilter(t, filter, modelType, isExactMatch))
					.collect(Collectors.toList());
		}
		return resultList;
	}

	public <TARGET> Optional<TARGET> findFirst(List<TARGET> dataList, String fieldName, String value,
			Type<TARGET> modelType) {
		Optional<TARGET> result = Optional.empty();
		if (dataList != null && dataList.size() > 0 && fieldName != null && fieldName.trim().length() > 0
				&& value != null && value.trim().length() > 0) {
			for (TARGET model : dataList) {
				Map<String, String> modelMap = mapper.map(model, modelType, MAP_STRING_STRING_TYPE);
				if (modelMap.containsKey(fieldName)) {
					String modelValue = modelMap.get(fieldName);
					if (modelValue != null && value.contentEquals(modelValue)) {
						result = Optional.of(model);
						break;
					}
				}
			}
		}
		return result;
	}

	public <TARGET> boolean isMatchFilter(TARGET model, TARGET filter, Type<TARGET> modelType, Boolean isExactMatch) {

		boolean isMatchFilter = true;
		if (filter == null) {
			return isMatchFilter;
		}
		if (model == null) {
			return isMatchFilter;
		}
		if (isExactMatch == null)
			isExactMatch = true;
		Map<String, String> modelMap = mapper.map(model, modelType, MAP_STRING_STRING_TYPE);
		Map<String, String> filterMap = mapper.map(filter, modelType, MAP_STRING_STRING_TYPE);

		Set<String> modelKeySet = modelMap.keySet();
		for (String eachKey : modelKeySet) {
			if (filterMap.containsKey(eachKey)) {
				String filterValue = filterMap.get(eachKey);
				String modelValue = modelMap.get(eachKey);
				if (filterValue != null && filterValue.trim().length() > 0) {
					if (modelValue == null || modelValue.trim().length() == 0) {
						isMatchFilter = false;
						break;
					} else if (isExactMatch) {
						if (!modelValue.contentEquals(filterValue)) {
							isMatchFilter = false;
							break;
						}
					} else {
						if (!modelValue.contains(filterValue)) {
							isMatchFilter = false;
							break;
						}

					}
				}
			}
		}
		return isMatchFilter;
	}

	public <TARGET> List<TARGET> filterAndPageable(List<TARGET> modelList, TARGET filter, Pageable pageable,
			Type<TARGET> modelType, Boolean isExactMatch) {
		List<TARGET> resultList = null;
		if (isExactMatch == null)
			isExactMatch = true;
		log.debug("To filter and pageable List: {}", modelList);
		resultList = filter(modelList, filter, modelType, isExactMatch);
		log.debug("filtered List: {}", resultList);
		resultList = sortAndSlice(resultList, pageable, modelType.getRawType());
		log.debug("filtered and sort+slice list: {}", resultList);
		return resultList;
	}

	public Map<String, String> mapFieldValueMapFromCriteria(String criteria) throws JSQLParserException {
		Map<String, String> criteriaMap = new HashMap<String, String>();
		if (criteria != null && criteria.trim().length() > 0) {
			Expression expr = CCJSqlParserUtil.parseCondExpression(criteria);
			expr.accept(new ExpressionVisitorAdapter() {

				@Override
				public void visit(LikeExpression expr) {
					String left = expr.getLeftExpression().toString();
					String right = expr.getRightExpression().toString();
					log.debug("left=" + left + "  op=" + expr.getStringExpression() + "  right=" + right);
					left = cleanseKey(left);
					right = cleanseValue(right);
					criteriaMap.put(left, right);
				}

				@Override
				protected void visitBinaryExpression(BinaryExpression expr) {
					if (expr instanceof ComparisonOperator) {
						String left = expr.getLeftExpression().toString();
						String right = expr.getRightExpression().toString();
						log.debug("left=" + left + "  op=" + expr.getStringExpression() + "  right=" + right);
						left = cleanseKey(left);
						right = cleanseValue(right);

						criteriaMap.put(left, right);
					}
					super.visitBinaryExpression(expr);
				}
			});
		}
		return criteriaMap;
	}

	private String cleanseKey(String key) {
		// remove className before dot(.)
		if (key != null) {
			if (key.contains(".")) {
				key = key.substring(key.indexOf(".") + 1);
			}
		}
		return key;
	}

	private String cleanseValue(String value) {
		if (value != null) {
			// remove single qoute
			value = value.replaceAll("'", "");
			// remove double qoute
			value = value.replaceAll("\"", "");
			// remove percent
			value = value.replaceAll("%", "");
		}
		return value;
	}

	public <ID> ID mapId(Object obj, Class<ID> idClass) {
		return mapper.map(obj, idClass);
	}

	public String encodeIdValues(Object idObject, String delimiter) throws UnsupportedEncodingException {
		log.debug("encodeIdSimple: " + idObject);

		String encodedId = null;
		Type<Object> idType = new TypeBuilder<Object>() {
		}.build();
		Type<SortedMap<String, String>> sortedMapType = new TypeBuilder<SortedMap<String, String>>() {
		}.build();
		SortedMap<String, String> keyValues = mapper.map(idObject, idType, sortedMapType);
		Collection<String> values = keyValues.values();
		String joinedId = String.join(delimiter, values);
		log.debug("joinedId: " + joinedId);
		encodedId = Base64.getUrlEncoder().encodeToString(joinedId.getBytes("UTF-8"));
		;
		return encodedId;
	}

	public <ID> ID decodeIdValues(String encodedId, Class<ID> idObjectClass, String delimiter)
			throws UnsupportedEncodingException {
		log.debug("decodeIdSimple: " + encodedId + ", idObjectClass=" + idObjectClass);
		ID idObject = null;
		byte[] decodedBytes = Base64.getUrlDecoder().decode(encodedId);
		String joinedString = new String(decodedBytes, "UTF-8");
		log.debug("joinedString: " + joinedString);
		String[] values = joinedString.split(delimiter);
		log.debug("values: " + Arrays.asList(values));
		SortedMap<String, String> keyValues = new TreeMap<String, String>();
		Field[] fields = idObjectClass.getDeclaredFields();
		List<String> keys = new ArrayList<String>();
		for (Field field : fields) {
//			log.debug("field: " + field.getName() + ", accessible: " + field.isAccessible());
			keys.add(field.getName());
		}
		Collections.sort(keys);
		int index = 0;
		for (String key : keys) {
			String value = values[index];
			log.debug("key: " + key + ", value: " + value);
			keyValues.put(key, value);
			index++;
		}
		idObject = mapper.map(keyValues, idObjectClass);
		return idObject;
	}

	public String encodeIdJson(Object idObject) {
		String encodedId = null;
		try {
			encodedId = objectMapper.writeValueAsString(idObject);
			log.debug("JSON: " + encodedId);
			encodedId = Base64.getEncoder().encodeToString(encodedId.getBytes());
			encodedId = Base64.getUrlEncoder().encodeToString(encodedId.getBytes());
			encodedId = "ID" + encodedId;
		} catch (JsonProcessingException e) {
			throw new BadRequestException("Invalid Id Object to be encoded: " + idObject, e);
		}
		return encodedId;
	}

	public <ID> ID decodeIdJson(String encodedId, Class<ID> idObjectClass) {
		ID idObject = null;
		try {
			encodedId = encodedId.substring(2);
			byte[] decodedIdBytes = Base64.getUrlDecoder().decode(encodedId);
			decodedIdBytes = Base64.getDecoder().decode(decodedIdBytes);
			String decodedId = new String(decodedIdBytes);
			idObject = objectMapper.readValue(decodedId, idObjectClass);
		} catch (JsonProcessingException e) {
			throw new BadRequestException("Invalid Encoded Id to be decoded: " + encodedId, e);
		}
		return idObject;
	}

	public String encodeId(Object idObject) {
		String encodedId = null;
		try {
			encodedId = encodeIdValues(idObject, ID_DELIMITER);
		} catch (UnsupportedEncodingException e) {
			encodedId = null;
			log.error("fail to encode Id: " + idObject, e);
		}
		return encodedId;
	}

	public <ID> ID decodeId(String encodedId, Class<ID> idObjectClass) {
		ID idObject = null;
		try {
			idObject = decodeIdValues(encodedId, idObjectClass, ID_DELIMITER);
		} catch (UnsupportedEncodingException e) {
			idObject = null;
			log.error("fail to decode Id: " + encodedId, e);
		}
		return idObject;
	}

	public String compress(String plainText) throws IOException {
		String compressedText = null;
		if (plainText == null || plainText.length() == 0) {
			return plainText;
		}
		log.debug("plainText length : " + plainText.length());
		log.debug("plainText Bytes length : " + plainText.getBytes("UTF-8").length);
		ByteArrayOutputStream obj = new ByteArrayOutputStream();
		GZIPOutputStream gzip = new GZIPOutputStream(obj);
		gzip.write(plainText.getBytes("UTF-8"));
		gzip.close();
		byte[] bytes = obj.toByteArray();
		compressedText = Base64.getUrlEncoder().encodeToString(bytes);
		log.debug("compressedText length : " + compressedText.length());
		log.debug("compressedText Bytes length : " + compressedText.getBytes("UTF-8").length);
		return compressedText;
	}

	public String uncompress(String str) throws UnsupportedEncodingException, IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		byte[] decodedIdBytes = Base64.getUrlDecoder().decode(str);
		log.debug("Input String length : " + str.length());
		GZIPInputStream gis = new GZIPInputStream(new ByteArrayInputStream(decodedIdBytes));
		BufferedReader bf = new BufferedReader(new InputStreamReader(gis, "UTF-8"));
		String outStr = "";
		String line;
		while ((line = bf.readLine()) != null) {
			outStr += line;
		}
		log.debug("Output String lenght : " + outStr.length());
		return outStr;
	}
	public Pageable mapPageable(PageableDto sourcePageableDto, Map<String, String> sourceToTargetPropertyMap) {
		Pageable sourcePageable = mapPageable(sourcePageableDto);
		return mapPageable(sourcePageable, sourceToTargetPropertyMap);
	}
	public Pageable mapPageable(Pageable sourcePageable, Map<String, String> sourceToTargetPropertyMap) {
		Pageable targetPageable = null;
		if(sourceToTargetPropertyMap==null || sourceToTargetPropertyMap.isEmpty()) {
			return sourcePageable;
		}
		if (sourcePageable != null) {
			Sort sourceSort = sourcePageable.getSort();
			Sort targetSort = null;
			if (sourceSort != null) {
				List<Order> targetOrderList = new ArrayList<Sort.Order>();
				for (Order sourceOrder : sourceSort) {
					String sourceProperty = sourceOrder.getProperty();
					Sort.Direction sourceDirection = sourceOrder.getDirection();
					String targetProperty = sourceProperty;
					if (sourceToTargetPropertyMap.containsKey(sourceProperty)) {
						targetProperty = sourceToTargetPropertyMap.get(sourceProperty);
					}
					if (sourceDirection.isAscending()) {
						targetOrderList.add(Order.asc(targetProperty));
					} else {
						targetOrderList.add(Order.desc(targetProperty));
					}
				}
				if(targetOrderList.size()>0)
				{
					targetSort = Sort.by(targetOrderList);
				}
			}
			if(targetSort!=null)
			{
				targetPageable = PageRequest.of(sourcePageable.getPageNumber(), sourcePageable.getPageSize(),targetSort);
			}else
			{
				targetPageable = PageRequest.of(sourcePageable.getPageNumber(), sourcePageable.getPageSize());
			}
		}
		return targetPageable;
	}

	public Pageable mapPageable(PageableDto pagebleDto) {
		Pageable pageable = null;
		if (pagebleDto != null) {
			Integer page = pagebleDto.getPage();
			Integer size = pagebleDto.getSize();
			List<String> sortProperties = pagebleDto.getSortProperties();
			Direction sortDirection = pagebleDto.getSortDirection();
			if (page == null || page < 0)
				page = 0;
			if (size == null || size < 1)
				size = 10;
			if (sortDirection == null)
				sortDirection = Direction.ASC;
			if (sortProperties == null || sortProperties.size() == 0) {
				pageable = PageRequest.of(page, size);
			} else {
				pageable = PageRequest.of(page, size, sortDirection, sortProperties.toArray(new String[] {}));
			}
		}
		return pageable;
	}

	public <MODEL> PageDto<MODEL> mapPageDto(Page<MODEL> modelPage) {
		PageDto<MODEL> modelPageDto = null;
		if (modelPage != null) {
			List<MODEL> content;
			Pageable pageable;
			Long total;

			content = modelPage.getContent();
			pageable = modelPage.getPageable();
			total = modelPage.getTotalElements();

			modelPageDto = new PageDto<MODEL>(content, pageable, total);
		}
		return modelPageDto;
	}
 
	public <SOURCE, TARGET> PageDto<TARGET> mapPageDto(Page<SOURCE> modelPage, Class<TARGET> targetClass) {
		PageDto<TARGET> modelPageDto = null;
		if (modelPage != null) {
			List<TARGET> content;
			Pageable pageable;
			Long total;

			content = mapper.mapAsList(modelPage.getContent(), targetClass);
			pageable = modelPage.getPageable();
			total = modelPage.getTotalElements();

			modelPageDto = new PageDto<TARGET>(content, pageable, total);
		}
		return modelPageDto;
	}

	public <TARGET> TARGET mapByCriteria(String criteria,Class<TARGET> targetClass) throws JSQLParserException {
		TARGET target = null;
		Map<String,String> fieldValueMap = mapFieldValueMapFromCriteria(criteria);
		target = map(fieldValueMap, targetClass);
		return target;
	}
	
	public <SOURCE, TARGET> Example<TARGET> mapExample(String criteria,Class<SOURCE> sourceClass, Class<TARGET> targetClass, Boolean isExactMatch) throws JSQLParserException {
		Map<String,String> fieldValueMap = mapFieldValueMapFromCriteria(criteria);
		SOURCE source = map(fieldValueMap, sourceClass);
		return mapExample(source, targetClass, isExactMatch);
	}
	
	public <SOURCE, TARGET> Example<TARGET> mapExample(SOURCE source, Class<TARGET> targetClass, Boolean isExactMatch) {
		Example<TARGET> targetExample = null;
		if (source != null) {
			TARGET target = mapper.map(source, targetClass);
			if (isExactMatch == null || isExactMatch) {
				targetExample = Example.of(target);
			} else {
				targetExample = Example.of(target, KEYWORD_MATCHER);
			}
		}
		return targetExample;
	}

	public <SOURCE, TARGET> Optional<TARGET> mapOptional(Optional<SOURCE> source, Class<TARGET> targetClass) {
		Optional<TARGET> target = Optional.empty();
		if (source != null && source.isPresent()) {
			target = Optional.of(mapper.map(source.get(), targetClass));
		}
		return target;
	}

	public <SOURCE, TARGET> TARGET map(SOURCE source, Class<TARGET> targetClass) {
		return (source == null ? null : mapper.map(source, targetClass));
	}

	public <SOURCE, TARGET> List<TARGET> mapAsList(List<SOURCE> source, Class<TARGET> targetClass) {
		return (source == null ? null : mapper.mapAsList(source, targetClass));
	}

	public MultiValueMap<String, String> mapQueryParameterMap(String queryParameterText) {
		LinkedMultiValueMap<String, String> queryParameterMap = new LinkedMultiValueMap<String, String>();
		if (queryParameterText != null && queryParameterText.trim().length() > 0) {
			String[] keyValues = queryParameterText.split(QUERY_PARAM_SET_DELIM);
			for (int i = 0; i < keyValues.length; i++) {
				String eachKeyValue = keyValues[i];
				String[] keyValue = eachKeyValue.split(QUERY_PARAM_KEY_VALUE_DELIM);
				String key = keyValue[0];
				String value = (keyValue.length > 1 ? keyValue[1] : "");
				queryParameterMap.add(key, value);
			}
		}
		return queryParameterMap;
	}

	public String mapQueryParameterText(MultiValueMap<String, String> queryParameterMap) {
		StringBuilder parametersTextBuilder = new StringBuilder(255);
		Boolean isFirst = true;
		if (queryParameterMap != null && queryParameterMap.size() > 0) {
			Set<String> keySet = queryParameterMap.keySet();
			for (String key : keySet) {
				List<String> valueList = queryParameterMap.get(key);
				for (String value : valueList) {
					if (isFirst) {
						isFirst = false;
					} else {
						parametersTextBuilder.append(QUERY_PARAM_SET_DELIM);
					}
					parametersTextBuilder.append(key).append(QUERY_PARAM_KEY_VALUE_DELIM).append(value);
				}
			}
		}
		return parametersTextBuilder.toString();
	}

	public MultiValueMap<String, String> mapMultiValueMapByMap(Map<String, String> valueMap) {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
		for (Entry<String, String> entry : valueMap.entrySet()) {
			multiValueMap.put(entry.getKey(), Collections.singletonList(entry.getValue()));
		}
		return multiValueMap;
	}

	public MultiValueMap<String, String> mapMultiValueMapByQueryString(String queryString)
			throws UnsupportedEncodingException {
		MultiValueMap<String, String> query_pairs = new LinkedMultiValueMap<String, String>();

		String[] pairs = queryString.split("&");
		for (String pair : pairs) {
			int idx = pair.indexOf("=");
			String key = URLDecoder.decode(pair.substring(0, idx), "UTF-8");
			String value = URLDecoder.decode(pair.substring(idx + 1), "UTF-8");
			if (query_pairs.containsKey(key)) {
				query_pairs.get(key).add(value);
			} else {
				query_pairs.put(key, Arrays.asList(value));
			}
			;
		}
		return query_pairs;
	}

	public Map<String, String> mapMapByMultiValueMap(MultiValueMap<String, String> multiValueMap) {
		Map<String, String> map = new HashMap<String, String>();
		for (Entry<String, List<String>> entry : multiValueMap.entrySet()) {
			map.put(entry.getKey(), entry.getValue().get(0));
		}
		return map;
	}

	public String mapUrlQueryString(Map<String, String> urlQueryParameterMap) throws UnsupportedEncodingException {
		StringBuilder sb = new StringBuilder();
		for (Map.Entry<String, String> entry : urlQueryParameterMap.entrySet()) {
			if (sb.length() > 0) {
				sb.append("&");
			}
			sb.append(String.format("%s=%s", URLEncoder.encode(entry.getKey(), "UTF-8"),
					URLEncoder.encode(entry.getValue(), "UTF-8")));
		}
		return sb.toString();
	}

	public List<String> mapPathVariableList(String uriPath) {
		List<String> variableList = new ArrayList<String>();
		final String PATTERN_TEXT = "\\{(.*?)\\}";
		Pattern pattern = Pattern.compile(PATTERN_TEXT);
		Matcher matcher = pattern.matcher(uriPath);
//		boolean matches = matcher.matches();
		while (matcher.find()) {
			variableList.add(matcher.group(1));
		}
		return variableList;
	}

	public String mapUriPath(String uriPath, MultiValueMap<String, String> parameterMap) {
		String resultPath = uriPath;
		if (uriPath != null && uriPath.trim().length() > 0 && parameterMap != null && parameterMap.size() > 0) {
			List<String> pathVariableList = mapPathVariableList(uriPath);
			for (String variable : pathVariableList) {
				if (parameterMap.containsKey(variable)) {
					List<String> valueList = parameterMap.get(variable);
					if (valueList.size() > 0) {
						String value = valueList.get(0);
						resultPath = resultPath.replace("{" + variable + "}", value);
					}
				}
			}
		}
		return resultPath;
	}

	public String mapCamelCaseFromKebabCase(String kebabCase) {
		final String DELIMITER = "-";
		// Run a loop till string
		// string contains underscore
		while (kebabCase.contains(DELIMITER)) {

			// Replace the first occurrence
			// of letter that present after
			// the underscore, to capitalize
			// form of next letter of underscore
			kebabCase = kebabCase.replaceFirst(DELIMITER + "[a-z]",
					String.valueOf(Character.toUpperCase(kebabCase.charAt(kebabCase.indexOf(DELIMITER) + 1))));
		}
		// Return string
		return kebabCase;
	}

	public String mapJson(Object obj) throws JsonProcessingException {
		return objectMapper.writeValueAsString(obj);
	}

	public <T> T mapObject(String json, TypeReference<T> typeReference)
			throws JsonMappingException, JsonProcessingException {
		return objectMapper.readValue(json, typeReference);
	}

	public <T> T mapObject(String json, Class<T> clazz) throws JsonMappingException, JsonProcessingException {
		return objectMapper.readValue(json, clazz);
	}

	public <T> Set<T> mapSet(@SuppressWarnings("unchecked") T... values) {
		return new HashSet<T>(Arrays.asList(values));
	}

	public OffsetDateTime mapOffsetDateTime(Date date) {
		return (date == null ? null : date.toInstant().atOffset(ZoneOffset.ofHours(7)));
	}

	public MultiValueMap<String, String> mapMultiValueMap(String key, String value) {
		MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<String, String>();
		multiValueMap.put(key, Collections.singletonList(value));
		return multiValueMap;
	}

	public String mapConcatString(String delimiter, String... strings) {
		StringBuilder builder = new StringBuilder();
		for (String str : strings) {
			if (str != null) {
				builder.append(str);
				if (delimiter != null)
					builder.append(delimiter);
			}
		}
		return builder.toString();
	}

	public <T> JsonNode valueToTree(T object) {
		return objectMapper.valueToTree(object);
	}

	public <T> T treeToValue(JsonNode jsonNode, Class<T> valueType)
			throws JsonProcessingException, IllegalArgumentException {
		return objectMapper.treeToValue(jsonNode, valueType);
	}
}
