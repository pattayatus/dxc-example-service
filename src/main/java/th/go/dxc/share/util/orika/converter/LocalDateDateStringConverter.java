package th.go.dxc.share.util.orika.converter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalDateDateStringConverter extends BidirectionalConverter<LocalDate, String> {
	private final DateTimeFormatter dateTimeFormatter;
	
	public LocalDateDateStringConverter(String datePattern) {
		super();
		this.dateTimeFormatter = new DateTimeFormatterBuilder().parseCaseInsensitive() .appendPattern(datePattern).toFormatter(Locale.ENGLISH);
	}

	@Override
	public String convertTo(LocalDate source, Type<String> destinationType, MappingContext mappingContext) {
		return source.format(dateTimeFormatter).toUpperCase();
	}

	@Override
	public LocalDate convertFrom(String source, Type<LocalDate> destinationType,
			MappingContext mappingContext) {
		return LocalDate.parse(source, dateTimeFormatter);
	}
	
}