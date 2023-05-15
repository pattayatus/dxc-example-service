package th.go.dxc.share.qm.api.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.glasnost.orika.MapperFacade;
import th.go.dxc.share.util.MapperUtil;

public class QmV2ApiMapper extends MapperUtil {

	public QmV2ApiMapper(ObjectMapper objectMapper, MapperFacade mapper) {
		super(objectMapper, mapper);
	}

}
