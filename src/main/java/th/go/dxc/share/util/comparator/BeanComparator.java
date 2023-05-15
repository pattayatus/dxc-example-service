package th.go.dxc.share.util.comparator;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

public class BeanComparator<T> implements Comparator<T>{

	private final Class<T> clazz;
	private final List<Order> orderList;

	public BeanComparator(Class<T> clazz, List<Order> orderList) {
		super();
		this.clazz = clazz;
		this.orderList = orderList;
	}

	@Override
	public int compare(T o1, T o2) {
		int result = 0;
		for (Iterator<Order> iterator = orderList.iterator(); iterator.hasNext();) {
			Order order = (Order) iterator.next();
			try {
				result = compare(o1,o2,order);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {				
				throw new RuntimeException(e);
			}
			// if not equals break but if equals compare next
			if(result!=0) break;				
		}
		return result;
	}
	@SuppressWarnings("unchecked")
	private int compare(T o1,T o2,Order order) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		int result = 0;
		Direction direction = order.getDirection();
		String property = order.getProperty();
		Field field = clazz.getDeclaredField(property);
		field.setAccessible(true);
		Object value1 = null;
		Object value2 = null;
		if(Direction.ASC.equals(direction))
		{
			value1 = field.get(o1);
			value2 = field.get(o2);
		}else
		{
			value1 = field.get(o2);
			value2 = field.get(o1);			
		}
		

		if (value1 instanceof Comparable)
		{
			if (value1 instanceof String) {			
				result =  ((String)value1).compareToIgnoreCase((String)value2);
			}
			else if(value1 instanceof Integer) {
				result =  ((Integer)value1).compareTo((Integer)value2);
			}				
			else if(value1 instanceof Double) {
				return ((Double)value1).compareTo((Double)value2);
			}				
			else if(value1 instanceof Float) {
				result =  ((Float)value1).compareTo((Float)value2);
			}else if(value1 instanceof LocalDate) {
				result =  ((LocalDate)value1).compareTo((LocalDate)value2);
			}else if(value1 instanceof LocalDate) {
				result =  ((LocalDate)value1).compareTo((LocalDate)value2);
			}else if(value1 instanceof LocalDateTime) {
				result =  ((LocalDateTime)value1).compareTo((LocalDateTime)value2);
			}else if(value1 instanceof OffsetDateTime) {
				result =  ((OffsetDateTime)value1).compareTo((OffsetDateTime)value2);
			}else if(value1 instanceof Date) {
				result =  ((Date)value1).compareTo((Date)value2);
			}else if(value1 instanceof Calendar) {
				result =  ((Calendar)value1).compareTo((Calendar)value2);
			}
			else {
				result =  ((Comparable<Object>)value1).compareTo(value2);
			}
		}
		else  // Compare as a String
		{
			result = value1.toString().compareToIgnoreCase(value2.toString());
		}
		return result;
	}
}
