package th.go.dxc.app.qm.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import th.go.dxc.app.qm.model.TsiPersonContact;
import th.go.dxc.app.qm.model.TsiPersonContactFilter;
import th.go.dxc.app.qm.util.TsiPersonContactOrikaMapperFactoryConfigurer;
import th.go.dxc.app.qm.util.TsiPersonContactQmServiceJpaImplMapper;
import th.go.dxc.infra.datasource.data_db.entity.TsiPersonContactEntity;
import th.go.dxc.infra.datasource.data_db.repository.TsiPersonContactRepository;
import th.go.dxc.share.config.OrikaMapperConfig;
import th.go.dxc.share.util.MapperUtil;

@Slf4j
class TsiPersonContactQmServiceJpaImplTest {

	private TsiPersonContactQmServiceJpaImpl service;
	private TsiPersonContactRepository repository;
	
	@BeforeEach
	public void setUp() {
		log.info("setUp");
		repository = Mockito.mock(TsiPersonContactRepository.class);
		ObjectMapper objectMapper = new ObjectMapper();
		OrikaMapperConfig orikaConfig = new OrikaMapperConfig();
		MapperFactory mapperFactory = orikaConfig.orikaMapperFactory(new DefaultMapperFactory.Builder(), Optional.of(Arrays.asList(new TsiPersonContactOrikaMapperFactoryConfigurer())));
		MapperFacade mapperFacade = mapperFactory.getMapperFacade();
		TsiPersonContactQmServiceJpaImplMapper mapper = new TsiPersonContactQmServiceJpaImplMapper(objectMapper, mapperFacade);
		service = new TsiPersonContactQmServiceJpaImpl(mapper, repository);
		

	
	}

	@Test
	void testFindAll_Without_filter_and_pageable() {		
		log.info("testFindAll_Without_filter_and_pageable");
		// prepare test data
		TsiPersonContactEntity firstEntity = new TsiPersonContactEntity(1L, "Alex", "Good");
		TsiPersonContactEntity secondEntity = new TsiPersonContactEntity(2L, "Bob", "Bad");
		Page<TsiPersonContactEntity> pageResult = new PageImpl<TsiPersonContactEntity>(Arrays.asList(firstEntity, secondEntity));
		Mockito.when(repository.findAll(MapperUtil.DEFAULT_PAGEABLE)).thenReturn(pageResult);
		
		// prepare expected data
		TsiPersonContact first = new TsiPersonContact(1L, "Alex", "Good");
		TsiPersonContact second = new TsiPersonContact(2L, "Bob", "Bad");
		Page<TsiPersonContact> expected = new PageImpl<TsiPersonContact>(Arrays.asList(first, second),
				MapperUtil.DEFAULT_PAGEABLE, 2L);
		
		// test
		Page<TsiPersonContact> result = service.findAll(null, null);

		// verify result
		assertThat(result).isNotNull();
		assertThat(result.getContent()).containsExactlyElementsOf(expected);
	}

	@Test
	void testFindAll_Filter_by_name_without_pageable() {		
		log.info("testFindAll_Filter_by_name_without_pageable");
		// prepare test data
		final String secondGivenName = "Bob";
		TsiPersonContactEntity secondEntity = new TsiPersonContactEntity(2L, secondGivenName, "Bad");
		Page<TsiPersonContactEntity> pageResult = new PageImpl<TsiPersonContactEntity>(Arrays.asList(secondEntity));
		Example<TsiPersonContactEntity> example = Example.of(new TsiPersonContactEntity(null, secondGivenName, null));
		Mockito.when(repository.findAll(example,MapperUtil.DEFAULT_PAGEABLE)).thenReturn(pageResult);
		
		// prepare expected data
		TsiPersonContact second = new TsiPersonContact(2L, secondGivenName, "Bad");
		Page<TsiPersonContact> expected = new PageImpl<TsiPersonContact>(Arrays.asList(second),
				MapperUtil.DEFAULT_PAGEABLE, 2L);
		
		// test
		TsiPersonContactFilter filter = new TsiPersonContactFilter(secondGivenName); 
		Page<TsiPersonContact> result = service.findAll(filter, null);

		// verify result
		assertThat(result).isNotNull();
		assertThat(result.getContent()).containsExactlyElementsOf(expected);
	}
	
	
	@Test
	void testFindByCriteria_Filter_by_name_without_pageable() {		
		log.info("testFindByCriteria_Filter_by_name_without_pageable");
		// prepare test data
		final String secondGivenName = "Bob";
		TsiPersonContactEntity secondEntity = new TsiPersonContactEntity(2L, secondGivenName, "Bad");
		Page<TsiPersonContactEntity> pageResult = new PageImpl<TsiPersonContactEntity>(Arrays.asList(secondEntity));
		Example<TsiPersonContactEntity> example = Example.of(new TsiPersonContactEntity(null, secondGivenName, null));
		Mockito.when(repository.findAll(example,MapperUtil.DEFAULT_PAGEABLE)).thenReturn(pageResult);
		
		// prepare expected data
		TsiPersonContact second = new TsiPersonContact(2L, secondGivenName, "Bad");
		Page<TsiPersonContact> expected = new PageImpl<TsiPersonContact>(Arrays.asList(second),
				MapperUtil.DEFAULT_PAGEABLE, 2L);
		
		// test
		String criteria = "givenName="+secondGivenName;
		Page<TsiPersonContact> result = service.findByCriteria(criteria, null);

		// verify result
		assertThat(result).isNotNull();
		assertThat(result.getContent()).containsExactlyElementsOf(expected);
	}

	@Test
	void testFindById_With_valid_id() {		
		log.info("testFindById_With_valid_id");
		// prepare test data
		final Long secondId = 2L;
		final String secondGivenName = "Bob";
		TsiPersonContactEntity secondEntity = new TsiPersonContactEntity(secondId, secondGivenName, "Bad");
		Mockito.when(repository.findById(secondId)).thenReturn(Optional.of(secondEntity));
		
		// prepare expected data
		TsiPersonContact expected = new TsiPersonContact(secondId, secondGivenName, "Bad");

		// test
		String testId = String.valueOf(secondId);
		Optional<TsiPersonContact> result = service.findById(testId);

		// verify result
		assertThat(result).isPresent().isEqualTo(expected);
	}

}
