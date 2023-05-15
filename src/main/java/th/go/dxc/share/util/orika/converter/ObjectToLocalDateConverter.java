package th.go.dxc.share.util.orika.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import ma.glasnost.orika.CustomConverter;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.Type;
import th.go.dxc.share.exception.InternalServerErrorException;

public class ObjectToLocalDateConverter extends CustomConverter<Object, LocalDate>{

	private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
	private ZoneOffset zoneOffset = ZoneOffset.of("+7");
	
	
	public ObjectToLocalDateConverter() {
		super();
	}
	
	public ObjectToLocalDateConverter(DateTimeFormatter dateTimeFormatter) {
		super();
		this.dateTimeFormatter = dateTimeFormatter;
	}



	public ObjectToLocalDateConverter(DateTimeFormatter dateTimeFormatter, ZoneOffset zoneOffset) {
		super();
		this.dateTimeFormatter = dateTimeFormatter;
		this.zoneOffset = zoneOffset;
	}




	@Override
	public LocalDate convert(Object source, Type<? extends LocalDate> destinationType, MappingContext mappingContext) {
		LocalDate destination = null;
		if(source!=null)
		{
			if(source instanceof LocalDate)
			{
				destination = (LocalDate)source;
			}else if(source instanceof String){
				destination = LocalDate.parse((String)source,dateTimeFormatter);
			}else if(source instanceof Long){
				Long epochMilli = (Long)source;
				
				destination = Instant.ofEpochMilli(epochMilli).atZone(zoneOffset).toLocalDate();
			}else if(source instanceof OffsetDateTime)
			{
				OffsetDateTime offsetDateTime = (OffsetDateTime)source;
				destination = offsetDateTime.toLocalDate();
			}else if(source instanceof LocalDateTime) {
				LocalDateTime localDateTime = (LocalDateTime)source;
				destination = localDateTime.toLocalDate();
			}else if(source instanceof Instant){
				Instant instant = (Instant)source;
				destination = instant.atZone(zoneOffset).toLocalDate();
			}else if(source instanceof Date)
			{
				Date date = (Date)source;
				destination = date.toInstant().atOffset(zoneOffset).toLocalDate();
			}else
			{
				throw new InternalServerErrorException("Invalid source type("+source.getClass()+") to convert to LocalDateTime");
			}
		}
		
		return destination;
	}

}
