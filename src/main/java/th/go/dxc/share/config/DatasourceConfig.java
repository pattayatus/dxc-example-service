package th.go.dxc.share.config;

import java.time.OffsetDateTime;
import java.util.Optional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(dateTimeProviderRef = "auditingOffsetDateTimeProvider")
public class DatasourceConfig {
	 	@Bean 
	    public DateTimeProvider auditingOffsetDateTimeProvider() {
	        return () -> Optional.of(OffsetDateTime.now());
	    }

}
