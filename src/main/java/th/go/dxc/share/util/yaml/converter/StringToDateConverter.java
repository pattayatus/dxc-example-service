package th.go.dxc.share.util.yaml.converter;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@ConfigurationPropertiesBinding
public class StringToDateConverter implements Converter<String, Date>{
	private DateTimeFormatter fullDateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME;
	private DateTimeFormatter dateFormatter = DateTimeFormatter.ISO_DATE;
	private ZoneOffset zoneOffset = ZoneOffset.of("+7");
	@Override
	public Date convert(String source) {
		log.debug("convert StringtoDate: {}",source);
		Date result = null;
		if(source!=null &&  source.trim().length()>=10)
		{
			
			source = source.trim();
			log.debug("convert string to date: "+source);
			if(source.length()==10)
			{
				log.debug("convert localdate");
				LocalDate localDate = LocalDate.parse(source, dateFormatter);
				result =java.util.Date.from(localDate.atStartOfDay()
					      .atZone(ZoneId.ofOffset("GMT", zoneOffset))
					      .toInstant());
			}else
			{
				log.debug("convert full date time");
				OffsetDateTime offsetDateTime = OffsetDateTime.parse(source, fullDateTimeFormatter);
				result = Date.from(offsetDateTime.toInstant());
			}
		}
		return result;
	}

}
