package th.go.dxc.share.qm.app.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

public abstract class AppMapperTemplate <APP_MODEL,APP_MODEL_ID,APP_MODEL_FILTER,INFRA_MODEL,INFRA_MODEL_ID,INFRA_MODEL_FILTER>{
	protected final Class<APP_MODEL> appModelClass;
	protected final Class<APP_MODEL_ID> appModelIdClass;
	protected final Class<APP_MODEL_FILTER> appModelFilterClass; 
	protected final Class<INFRA_MODEL> infraModelClass;
	protected final Class<INFRA_MODEL_ID> infraModelIdClass;
	protected final Class<INFRA_MODEL_FILTER> infraModelFilterClass;
	protected MapperFacade mapper;
	public AppMapperTemplate(
			MapperFactory mapperFactory,
			Class<APP_MODEL> appModelClass, 
			Class<APP_MODEL_ID> appModelIdClass,
			Class<APP_MODEL_FILTER> appModelFilterClass, 
			Class<INFRA_MODEL> infraModelClass, 
			Class<INFRA_MODEL_ID> infraModelIdClass,
			Class<INFRA_MODEL_FILTER> infraModelFilterClass
			) {
		super();
		this.appModelClass = appModelClass;
		this.appModelIdClass = appModelIdClass;
		this.appModelFilterClass = appModelFilterClass;
		this.infraModelClass = infraModelClass;
		this.infraModelIdClass = infraModelIdClass;
		this.infraModelFilterClass = infraModelFilterClass;
		initMapper(mapperFactory);
	}

	public AppMapperTemplate(
			Class<APP_MODEL> appModelClass, 
			Class<APP_MODEL_ID> appModelIdClass,
			Class<APP_MODEL_FILTER> appModelFilterClass, 
			Class<INFRA_MODEL> infraModelClass, 
			Class<INFRA_MODEL_ID> infraModelIdClass,
			Class<INFRA_MODEL_FILTER> infraModelFilterClass
			) {
		super();
		this.appModelClass = appModelClass;
		this.appModelIdClass = appModelIdClass;
		this.appModelFilterClass = appModelFilterClass;
		this.infraModelClass = infraModelClass;
		this.infraModelIdClass = infraModelIdClass;
		this.infraModelFilterClass = infraModelFilterClass;
	}
	
	public void initMapper(MapperFactory mapperFactory)
	{
		configAppModelToInfraModelMapping(mapperFactory.classMap(this.appModelClass, this.infraModelClass)).register();
		configAppModelFilterToInfraModelFilter(mapperFactory.classMap(this.appModelFilterClass, this.infraModelFilterClass)).register();
		configAppModelIdToInfraModelId(mapperFactory.classMap(this.appModelIdClass, this.infraModelIdClass)).register();		
		this.mapper = mapperFactory.getMapperFacade();
	}
	
	public abstract ClassMapBuilder<APP_MODEL, INFRA_MODEL> configAppModelToInfraModelMapping(ClassMapBuilder<APP_MODEL, INFRA_MODEL> classMapBuilder);
	public abstract ClassMapBuilder<APP_MODEL_ID, INFRA_MODEL_ID> configAppModelIdToInfraModelId(ClassMapBuilder<APP_MODEL_ID, INFRA_MODEL_ID> classMapBuilder);
	public abstract ClassMapBuilder<APP_MODEL_FILTER, INFRA_MODEL_FILTER> configAppModelFilterToInfraModelFilter(ClassMapBuilder<APP_MODEL_FILTER, INFRA_MODEL_FILTER> classMapBuilder);
	
	
	
	public Example<INFRA_MODEL> mapInfraModelFilter(APP_MODEL_FILTER appModelFilter){
		INFRA_MODEL infraModel = mapper.map(appModelFilter, infraModelClass);
		return Example.of(infraModel);
	}
	
	public Optional<APP_MODEL> mapOptionalAppModel(Optional<INFRA_MODEL> infraModel){
		Optional<APP_MODEL> appModel = Optional.empty();
		if(infraModel.isPresent())
		{
			appModel = Optional.of(this.mapAppModel(infraModel.get()));
		}
		return appModel;
	}
	
	public APP_MODEL mapAppModel(INFRA_MODEL infraModel){
		APP_MODEL appModel = null;
		if(infraModel !=null )
		{
			appModel = mapper.map(infraModel, appModelClass);
		}
		return appModel;
	}
	
	public APP_MODEL_ID mapAppModelId(INFRA_MODEL_ID infraModelId) {
		APP_MODEL_ID appModelId = null;
		if(infraModelId!=null)
		{
			appModelId = mapper.map(infraModelId, appModelIdClass);
		}
		return appModelId;
	}
	
	public Page<APP_MODEL> mapAppModelPage(Page<INFRA_MODEL> infraModelPage){
		List<INFRA_MODEL> infraModelList = infraModelPage.getContent();
		Pageable pageable =  infraModelPage.getPageable();
		Long total = infraModelPage.getTotalElements();		
		List<APP_MODEL> appModelList = new ArrayList<APP_MODEL>();
		for (Iterator<INFRA_MODEL> iterator = infraModelList.iterator(); iterator.hasNext();) {
			INFRA_MODEL infraModel = iterator.next();
			APP_MODEL appModel = this.mapAppModel(infraModel);
			appModelList.add(appModel);
		}
		return new PageImpl<APP_MODEL>(appModelList,pageable,total);
	}
	
	public INFRA_MODEL_ID mapInfraModelId(APP_MODEL_ID appModelId) {
		INFRA_MODEL_ID infraModelId = null;
		if(appModelId!=null)
		{
			infraModelId = mapper.map(appModelId, infraModelIdClass);
		}
		return infraModelId;
	}
	
	public INFRA_MODEL_ID getInfraModelId(INFRA_MODEL infraModel) {
		INFRA_MODEL_ID infraModelId = null;
		if(infraModel!=null) {
			infraModelId = mapper.map(infraModel, infraModelIdClass);
		}
		return infraModelId;
	}
	
	

}
