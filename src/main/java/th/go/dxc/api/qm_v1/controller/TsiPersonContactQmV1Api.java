package th.go.dxc.api.qm_v1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import th.go.dxc.api.qm_v1.dto.TsiPersonContactQmV1ResultDto;
import th.go.dxc.api.qm_v1.util.TsiPersonContractQmV1ApiMapper;
import th.go.dxc.app.qm.model.TsiPersonContact;
import th.go.dxc.app.qm.model.TsiPersonContactFilter;
import th.go.dxc.share.qm.api.controller.QmV1ApiTemplate;
import th.go.dxc.share.qm.app.service.QmService;
@Api(description = "บริการค้นหาข้อมูล "+TsiPersonContact.METADATA_NAME+" "+TsiPersonContact.METADATA_ORG+" เวอร์ขั่น 1")
@RestController
@RequestMapping("/api/qm/v1/"+TsiPersonContact.METADATA_ORG_CODE+"/"+TsiPersonContact.METADATA_RESOURCES)
public class TsiPersonContactQmV1Api extends QmV1ApiTemplate<TsiPersonContact, TsiPersonContactFilter, TsiPersonContactQmV1ResultDto>{

	public TsiPersonContactQmV1Api(QmService<TsiPersonContact, TsiPersonContactFilter> service, TsiPersonContractQmV1ApiMapper mapper) {
		super(service, mapper,TsiPersonContactQmV1ResultDto.class);
	}

	

}
