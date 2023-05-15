package th.go.dxc.share.util.orika.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import th.go.dxc.share.exception.InternalServerErrorException;

public class ObjectToLocalDateTimeConverter extends CustomConverter<Object, LocalDateTime>{

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
	private ZoneOffset zoneOffset = ZoneOffset.of("+7");
	
	
	public ObjectToLocalDateTimeConverter() {
		super();
	}
	
	public ObjectToLocalDateTimeConverter(DateTimeFormatter dateTimeFormatter) {
		super();
		this.dateTimeFormatter = dateTimeFormatter;
	}

	public ObjectToLocalDateTimeConverter(DateTimeFormatter dateTimeFormatter, ZoneOffset zoneOffset) {
		super();
		this.dateTimeFormatter = dateTimeFormatter;
		this.zoneOffset = zoneOffset;
	}

	@Override
	public LocalDateTime convert(Object source, Type<? extends LocalDateTime> destinationType, MappingContext mappingContext) {
		LocalDateTime destination = null;
		if(source!=null)
		{
			if(source instanceof LocalDateTime)
			{
				destination = (LocalDateTime)source;
			}else if(source instanceof String){
				destination = LocalDateTime.parse((String)source,dateTimeFormatter);
			}else if(source instanceof Long){
				Long epochMilli = (Long)source;
				destination = LocalDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), zoneOffset);
			}else if(source instanceof LocalDate)
			{
				LocalDate localDate = (LocalDate)source;
				destination = LocalDateTime.of(localDate, LocalTime.MIN);
			}else if(source instanceof OffsetDateTime) {
				OffsetDateTime offsetDateTime = (OffsetDateTime)source;
				destination = offsetDateTime.toLocalDateTime();
				
			}else if(source instanceof Instant){
				Instant instant = (Instant)source;
				destination = LocalDateTime.ofInstant(instant, zoneOffset);
			}else if(source instanceof Date)
			{
				Date date = (Date)source;
				destination = date.toInstant().atOffset(zoneOffset).toLocalDateTime();
			}else
			{
				throw new InternalServerErrorException("Invalid source type("+source.getClass()+") to convert to LocalDateTime");
			}
		}
		
		return destination;
	}

}
