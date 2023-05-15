package th.go.dxc.share.qm.app.util;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.ExpressionVisitorAdapter;
import net.sf.jsqlparser.expression.operators.relational.ComparisonOperator;
import net.sf.jsqlparser.expression.operators.relational.LikeExpression;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import th.go.dxc.share.exception.BadRequestException;
import th.go.dxc.share.util.comparator.BeanComparator;
@Slf4j
public class MapperUtils {
	private final static ObjectMapper objectMapper = new ObjectMapper();
	private final static MapperFacade mapper = new DefaultMapperFactory.Builder().build().getMapperFacade();
	
	public static <T> List<T> sortAndSlice(List<T> modelList, Pageable pageable,Class<T> modelClass){
		List<T> processedList = new ArrayList<T>(modelList);
		List<Order> orderList = pageable.getSort().toList();
		processedList.sort(new BeanComparator<T>(modelClass, orderList));
		Long startIndex = pageable.getOffset();
		Long endIndex = startIndex+pageable.getPageSize();
		if(endIndex>modelList.size())endIndex = Long.valueOf(modelList.size());
		processedList =  processedList.subList(startIndex.intValue(), endIndex.intValue());
		return processedList;
	}
	public static Map<String, String> parseCriteria(String criteria) throws JSQLParserException {
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

	private static String cleanseKey(String key) {
		// remove className before dot(.)
		if (key != null) {
			if (key.contains(".")) {
				key = key.substring(key.indexOf(".") + 1);
			}
		}
		return key;
	}
	private static String cleanseValue(String value) {
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
	public static <ID> ID mapId(Object obj,Class<ID> idClass) {
    	return mapper.map(obj, idClass);
    }
	public static String encodeId(Object idObject) {
		String encodedId = null;
		try {
			encodedId = objectMapper.writeValueAsString(idObject);
			log.debug("JSON: "+encodedId);
			encodedId = Base64.getEncoder().encodeToString(encodedId.getBytes());
			encodedId = Base64.getUrlEncoder().encodeToString(encodedId.getBytes());
			encodedId = "ID"+encodedId;
		} catch (JsonProcessingException e) {
			throw new BadRequestException("Invalid Id Object to be encoded: "+idObject, e);
		}
		return encodedId;
	}
	public static <ID> ID decodeId(String encodedId, Class<ID> idObjectClass) {
		ID idObject = null;
		try {
			encodedId = encodedId.substring(2);
			byte[] decodedIdBytes = Base64.getUrlDecoder().decode(encodedId);
			decodedIdBytes = Base64.getDecoder().decode(decodedIdBytes);
			String decodedId = new String(decodedIdBytes);
			idObject = objectMapper.readValue(decodedId, idObjectClass);
		} catch (JsonProcessingException e) {
			throw new BadRequestException("Invalid Encoded Id to be decoded: "+encodedId, e);
		}
		return idObject;
	}
}
