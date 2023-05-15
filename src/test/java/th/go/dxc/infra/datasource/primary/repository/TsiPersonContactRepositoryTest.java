package th.go.dxc.infra.datasource.primary.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.jeasy.random.EasyRandom;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.EnabledIf;

import lombok.extern.slf4j.Slf4j;
import th.go.dxc.infra.datasource.data_db.entity.TsiPersonContactEntity;
import th.go.dxc.infra.datasource.data_db.repository.TsiPersonContactRepository;
import th.go.dxc.share.config.RandomConfig;
//@EnabledIf(expression = "#{environment.acceptsProfiles('sit')}", loadContext = false)
@Slf4j
@EnabledIf("true")
@ActiveProfiles("test")
@DataJpaTest(showSql = true)
@EnableJpaRepositories(basePackages = {"th.go.dxc.infra.datasource.primary.repository"})
@EntityScan(basePackages = {"th.go.dxc.infra.datasource.primary.entity"})
@Import(value= {RandomConfig.class})
class TsiPersonContactRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private TsiPersonContactRepository repository;

	@Autowired
	private EasyRandom easyRandom = new EasyRandom();

	@Test
	void testFindById() {
		TsiPersonContactEntity toSaveEntity = easyRandom.nextObject(TsiPersonContactEntity.class);
		TsiPersonContactEntity savedEntity = this.entityManager.persist(toSaveEntity);
		
		Optional<TsiPersonContactEntity> entity = this.repository.findById(savedEntity.getPersonId());
		log.debug("entity: "+entity);
		assertThat(entity).isNotNull().isPresent();
		assertThat(entity.get().getPersonId()).isEqualTo(savedEntity.getPersonId());
	}

}
