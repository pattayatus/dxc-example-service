package th.go.dxc.share.util.orika.converter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class OffsetDateTimeDateStringConverter extends BidirectionalConverter<OffsetDateTime, String> {
	private final DateTimeFormatter dateTimeFormatter;
	private final ZoneOffset zoneOffset;
	public OffsetDateTimeDateStringConverter(String datePattern,ZoneOffset zoneOffset) {
		super();
		this.dateTimeFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive() .appendPattern(datePattern).toFormatter(Locale.ENGLISH);
		this.zoneOffset = zoneOffset;
	}

	@Override
	public String convertTo(OffsetDateTime source, Type<String> destinationType, MappingContext mappingContext) {
		return source.format(dateTimeFormatter).toUpperCase();
	}

	@Override
	public OffsetDateTime convertFrom(String source, Type<OffsetDateTime> destinationType,
			MappingContext mappingContext) {
		return OffsetDateTime.of(LocalDate.parse(source, dateTimeFormatter), LocalTime.MIN, zoneOffset);
	}
	
}