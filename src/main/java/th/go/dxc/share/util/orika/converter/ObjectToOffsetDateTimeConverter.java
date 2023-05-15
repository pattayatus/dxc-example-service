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

public class ObjectToOffsetDateTimeConverter extends CustomConverter<Object, OffsetDateTime>{

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
	private ZoneOffset zoneOffset = ZoneOffset.of("+7");
	
	
	public ObjectToOffsetDateTimeConverter() {
		super();
	}
	
	public ObjectToOffsetDateTimeConverter(DateTimeFormatter dateTimeFormatter) {
		super();
		this.dateTimeFormatter = dateTimeFormatter;
	}



	public ObjectToOffsetDateTimeConverter(DateTimeFormatter dateTimeFormatter, ZoneOffset zoneOffset) {
		super();
		this.dateTimeFormatter = dateTimeFormatter;
		this.zoneOffset = zoneOffset;
	}




	@Override
	public OffsetDateTime convert(Object source, Type<? extends OffsetDateTime> destinationType, MappingContext mappingContext) {
		OffsetDateTime destination = null;
		if(source!=null)
		{
			if(source instanceof OffsetDateTime)
			{
				destination = (OffsetDateTime)source;
			}else if(source instanceof String){
				destination = OffsetDateTime.parse((String)source,dateTimeFormatter);
			}else if(source instanceof Long){
				Long epochMilli = (Long)source;
				destination = OffsetDateTime.ofInstant(Instant.ofEpochMilli(epochMilli), zoneOffset);
			}else if(source instanceof LocalDate)
			{
				LocalDate localDate = (LocalDate)source;
				destination = OffsetDateTime.of(localDate, LocalTime.MIN, zoneOffset);
			}else if(source instanceof LocalDateTime) {
				LocalDateTime localDateTime = (LocalDateTime)source;
				destination = OffsetDateTime.of(localDateTime, zoneOffset);
			}else if(source instanceof Instant){
				Instant instant = (Instant)source;
				destination = OffsetDateTime.ofInstant(instant, zoneOffset);
			}else if(source instanceof Date)
			{
				Date date = (Date)source;
				destination = date.toInstant().atOffset(zoneOffset);
			}else
			{
				throw new InternalServerErrorException("Invalid source type("+source.getClass()+") to convert to LocalDateTime");
			}
		}
		
		return destination;
	}

}
