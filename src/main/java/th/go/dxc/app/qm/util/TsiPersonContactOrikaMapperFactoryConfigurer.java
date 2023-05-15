package th.go.dxc.app.qm.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.Type;
import ma.glasnost.orika.metadata.TypeBuilder;
import th.go.dxc.app.qm.model.TsiPersonContact;
import th.go.dxc.app.qm.model.TsiPersonContactFilter;
import th.go.dxc.infra.datasource.data_db.entity.TsiPersonContactEntity;
import th.go.dxc.share.util.orika.OrikaMapperFactoryConfigurer;

@Slf4j
@Component
public class TsiPersonContactOrikaMapperFactoryConfigurer implements OrikaMapperFactoryConfigurer{
	public final static Type<TsiPersonContact> DOMAIN_TYPE = new TypeBuilder<TsiPersonContact>() {}.build();
	public final static Type<TsiPersonContactFilter> DOMAIN_FILTER_TYPE = new TypeBuilder<TsiPersonContactFilter>() {}.build();
	public final static Type<TsiPersonContactEntity> ENTITY_TYPE = new TypeBuilder<TsiPersonContactEntity>() {}.build();

	public final static Map<String,String> PAGEABLE_DIF_PROPERTY_MAP = new HashMap<String, String>();
	
	static {
		PAGEABLE_DIF_PROPERTY_MAP.put("id", "personId");
	}

	
	//https://orika-mapper.github.io/orika-docs/mappings-via-classmapbuilder.html
	@Override
	public void configureMapping(MapperFactory orikaMapperFactory) {
		log.debug("configureMapping {}"+TsiPersonContactQmServiceJpaImplMapper.class.getName());
		
		
		orikaMapperFactory.classMap(DOMAIN_TYPE, ENTITY_TYPE)
		.field("id","personId")
		.field("personId", "personId")
		.byDefault()
		.register();
		
		orikaMapperFactory.classMap(DOMAIN_FILTER_TYPE, ENTITY_TYPE)
		.byDefault()
		.register();
		
		
	}
}
