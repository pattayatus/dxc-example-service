package th.go.dxc.share.util.yaml.converter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ConfigurationPropertiesBinding
public class StringToOffsetDateTimeConverter implements Converter<String, OffsetDateTime>{
	private DateTimeFormatter fullDateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
	private ZoneOffset zoneOffset = ZoneOffset.of("+7");
	@Override
	public OffsetDateTime convert(String source) {
		OffsetDateTime result = null;
		if(source!=null &&  source.trim().length()>=10)
		{
			
			source = source.trim();
			log.debug("convert string to date: "+source);
			if(source.length()==10)
			{
				log.debug("convert localdate");
				LocalDate localDate = LocalDate.parse(source, dateFormatter);
				result = OffsetDateTime.of(localDate, LocalTime.MIN, zoneOffset);
			}else
			{
				log.debug("convert offsetdatetime");
				result = OffsetDateTime.parse(source, fullDateTimeFormatter);
			}
		}
		return result;
	}

}
