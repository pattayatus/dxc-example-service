package th.go.dxc.share.qm.app.util;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.metadata.ClassMapBuilder;
import net.sf.jsqlparser.JSQLParserException;
import th.go.dxc.share.exception.BadRequestException;
import th.go.dxc.share.qm.app.model.QmModel;

@Slf4j
public class QmMapperTemplate<APP_MODEL extends QmModel,APP_MODEL_FILTER,INFRA_MODEL,INFRA_MODEL_ID,INFRA_MODEL_FILTER> extends AppMapperTemplate<APP_MODEL, String, APP_MODEL_FILTER, INFRA_MODEL, INFRA_MODEL_ID, INFRA_MODEL_FILTER>{

	private final String infraModelIdFieldName;
	
	public QmMapperTemplate(MapperFactory mapperFactory, 
			Class<APP_MODEL> appModelClass, 
			Class<APP_MODEL_FILTER> appModelFilterClass, 
			Class<INFRA_MODEL> infraModelClass,
			Class<INFRA_MODEL_ID> infraModelIdClass, 
			Class<INFRA_MODEL_FILTER> infraModelFilterClass,
			String infraModelIdFieldName
		) {
		super(appModelClass, String.class, appModelFilterClass, infraModelClass, infraModelIdClass,
				infraModelFilterClass);		
		this.infraModelIdFieldName = infraModelIdFieldName;
		this.initMapper(mapperFactory);
	}

	@Override
	public ClassMapBuilder<APP_MODEL, INFRA_MODEL> configAppModelToInfraModelMapping(
			ClassMapBuilder<APP_MODEL, INFRA_MODEL> classMapBuilder) {
		log.debug("infraModelIdFieldName: Child with "+infraModelIdFieldName);
		if(infraModelIdFieldName!=null)
		{
			log.debug("configAppModelToInfraModelMapping: "+QmModel.ID_FIELD_NAME+"-"+this.infraModelIdFieldName);
			classMapBuilder.field(QmModel.ID_FIELD_NAME,this.infraModelIdFieldName);
			classMapBuilder.field(this.infraModelIdFieldName,this.infraModelIdFieldName);
		}else {
			log.debug("No infraModelIdFieldName");
			classMapBuilder.customize(new CustomMapper<APP_MODEL,INFRA_MODEL>() {
				@Override
				public void mapBtoA(INFRA_MODEL b, APP_MODEL a, MappingContext context) {
					super.mapBtoA(b, a, context);
					INFRA_MODEL_ID infraModelId = mapper.map(b, infraModelIdClass);
					a.setId(MapperUtils.encodeId(infraModelId));
				}
			});
		}
		return classMapBuilder.byDefault();
	}
	@Override
	public ClassMapBuilder<String, INFRA_MODEL_ID> configAppModelIdToInfraModelId(
			ClassMapBuilder<String, INFRA_MODEL_ID> classMapBuilder) {
		return classMapBuilder.byDefault();
	}

	@Override
	public ClassMapBuilder<APP_MODEL_FILTER, INFRA_MODEL_FILTER> configAppModelFilterToInfraModelFilter(
			ClassMapBuilder<APP_MODEL_FILTER, INFRA_MODEL_FILTER> classMapBuilder) {
		return classMapBuilder.byDefault();
	}

	@Override
	public INFRA_MODEL_ID mapInfraModelId(String appModelId) {
		INFRA_MODEL_ID infraModelId = null;
		if(infraModelIdFieldName!=null)
		{
			infraModelId =  super.mapInfraModelId(appModelId);
		}else
		{
			infraModelId = MapperUtils.decodeId(appModelId, infraModelIdClass);
		}
		return infraModelId;
		
	}

	public APP_MODEL_FILTER mapAppModelFilter(String criteria) {
		APP_MODEL_FILTER modelFilter = null;
		Map<String, String> criteriaMap;
		try {
			criteriaMap = MapperUtils.parseCriteria(criteria);
			modelFilter = mapper.map(criteriaMap, appModelFilterClass);
		} catch (JSQLParserException e) {
			throw new BadRequestException("Invalid Criteria: "+criteria+" "+e.getLocalizedMessage(), e);
		}
		return modelFilter;
	}

	
}
