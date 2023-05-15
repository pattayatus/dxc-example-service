package th.go.dxc.api.qm_v1.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.glasnost.orika.MapperFacade;
import th.go.dxc.app.qm.model.TsiPersonContact;
import th.go.dxc.app.qm.model.TsiPersonContactFilter;
import th.go.dxc.share.qm.api.util.QmV1ApiMapper;

@Component
public class TsiPersonContractQmV1ApiMapper extends QmV1ApiMapper<TsiPersonContact, TsiPersonContactFilter>{

	public TsiPersonContractQmV1ApiMapper(ObjectMapper objectMapper, MapperFacade mapper) {
		super(objectMapper, mapper);
	}

}
