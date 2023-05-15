package th.go.dxc.app.qm.util;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import ma.glasnost.orika.MapperFacade;
import th.go.dxc.share.util.MapperUtil;
@Component
public class TsiPersonContactQmServiceJpaImplMapper extends MapperUtil {

	
	public TsiPersonContactQmServiceJpaImplMapper(ObjectMapper objectMapper, MapperFacade mapper) {
		super(objectMapper, mapper);
	}


}
