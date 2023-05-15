package th.go.dxc.share.util.orika.converter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalDateToOffsetDateTimeConverter extends BidirectionalConverter<LocalDate, OffsetDateTime> {
	private final ZoneOffset zoneOffset;
	

	public LocalDateToOffsetDateTimeConverter() {
		super();
		ZoneId  systemZoneId = ZoneOffset.systemDefault();
		this.zoneOffset = systemZoneId.getRules().getOffset(Instant.now());
	}
	
	
	public LocalDateToOffsetDateTimeConverter(ZoneOffset zoneOffset) {
		super();
		this.zoneOffset = zoneOffset;
	}

	@Override
	public OffsetDateTime convertTo(LocalDate source, Type<OffsetDateTime> destinationType, MappingContext mappingContext) {
		return OffsetDateTime.of(source, LocalTime.MIN, zoneOffset);
	}

	@Override
	public LocalDate convertFrom(OffsetDateTime source, Type<LocalDate> destinationType,
			MappingContext mappingContext) {
		return source.toLocalDate();
	}
	
}