package th.go.dxc.api.qm_v2.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.glasnost.orika.MapperFacade;
import th.go.dxc.share.qm.api.util.QmV2ApiMapper;

@Component
public class TsiPersonContactQmV2ApiMapper extends QmV2ApiMapper{

	public TsiPersonContactQmV2ApiMapper(ObjectMapper objectMapper, MapperFacade mapper) {
		super(objectMapper, mapper);
	}

}
