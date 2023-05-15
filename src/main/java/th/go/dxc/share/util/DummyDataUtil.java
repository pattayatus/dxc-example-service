package th.go.dxc.share.util;

import java.util.ArrayList;
import java.util.List;

import org.jeasy.random.EasyRandom;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.repository.CrudRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DummyDataUtil {
	private final ConfigurableApplicationContext ctx;
	private final EasyRandom easyRandom;
	public DummyDataUtil(ConfigurableApplicationContext ctx) {
		super();
		this.ctx = ctx;
		this.easyRandom = ctx.getBean(EasyRandom.class);
	}
	
	public <ENTITY,ENTITY_ID,REPOSITORY extends CrudRepository<ENTITY,ENTITY_ID>> List<ENTITY> insertDummyData(Class<ENTITY> entityClass,Class<REPOSITORY> repositoryClass,Integer numData){
		List<ENTITY> savedEntityList = new ArrayList<ENTITY>();
		CrudRepository<ENTITY,ENTITY_ID> repository = ctx.getBean(repositoryClass);
		for (int i = 0; i < numData; i++) {
			ENTITY entity = easyRandom.nextObject(entityClass);
			log.info("Saving: "+entity);
			ENTITY savedEntity = repository.save(entity);
			savedEntityList.add(savedEntity);
			log.debug("Saved Entity: "+savedEntity);	    			
		}
		return savedEntityList;
	}
	
	public <T> List<T> randomList(Class<T> clazz,int num){
		List<T> randomList = new ArrayList<T>(num);
		for(int i=0;i<num;i++)
		{
			randomList.add(easyRandom.nextObject(clazz));
		}
		return randomList;
	}
	
}
