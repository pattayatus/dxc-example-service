package th.go.dxc.share.util.orika.converter;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;

public class LocalDateTimeToOffsetDateTimeConverter extends BidirectionalConverter<LocalDateTime, OffsetDateTime> {
	private final ZoneOffset zoneOffset;
	

	public LocalDateTimeToOffsetDateTimeConverter() {
		super();
		ZoneId  systemZoneId = ZoneOffset.systemDefault();
		this.zoneOffset = systemZoneId.getRules().getOffset(Instant.now());
	}
	
	
	public LocalDateTimeToOffsetDateTimeConverter(ZoneOffset zoneOffset) {
		super();
		this.zoneOffset = zoneOffset;
	}

	@Override
	public OffsetDateTime convertTo(LocalDateTime source, Type<OffsetDateTime> destinationType, MappingContext mappingContext) {
		return OffsetDateTime.of(source, zoneOffset);
	}

	@Override
	public LocalDateTime convertFrom(OffsetDateTime source, Type<LocalDateTime> destinationType,
			MappingContext mappingContext) {
		return source.toLocalDateTime();
	}
	
}