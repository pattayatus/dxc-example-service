package th.go.dxc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.core.SpringVersion;

import lombok.extern.slf4j.Slf4j;
import th.go.dxc.infra.datasource.data_db.entity.TsiPersonContactEntity;
import th.go.dxc.infra.datasource.data_db.repository.TsiPersonContactRepository;
import th.go.dxc.share.util.DummyDataUtil;

@SpringBootApplication
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@Slf4j
public class DxcExampleServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DxcExampleServiceApplication.class, args);
	}

	@EventListener(ApplicationReadyEvent.class)
	public void initDataAfterStartup(ApplicationReadyEvent event) {
		log.info(SpringVersion.getVersion());
		log.info(SpringBootVersion.getVersion());
		log.info("initDataAfterStartup: " + event);
		ConfigurableApplicationContext ctx = event.getApplicationContext();
		String[] activeProfiles = ctx.getEnvironment().getActiveProfiles();
		List<String> activeProfileList = (activeProfiles==null?new ArrayList<String>():Arrays.asList(activeProfiles));
		if (activeProfiles != null && activeProfileList.contains("dev")) {
			DummyDataUtil dummyDataUtil = new DummyDataUtil(ctx);			
			dummyDataUtil.insertDummyData(TsiPersonContactEntity.class, TsiPersonContactRepository.class, 100);
		}
	}
}


