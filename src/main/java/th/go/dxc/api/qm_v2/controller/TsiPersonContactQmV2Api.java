package th.go.dxc.api.qm_v2.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import th.go.dxc.api.qm_v2.util.TsiPersonContactQmV2ApiMapper;
import th.go.dxc.app.qm.model.TsiPersonContact;
import th.go.dxc.app.qm.model.TsiPersonContactFilter;
import th.go.dxc.share.exception.BadGatewayException;
import th.go.dxc.share.exception.BadRequestException;
import th.go.dxc.share.exception.InternalServerErrorException;
import th.go.dxc.share.exception.NotFoundException;
import th.go.dxc.share.model.PageDto;
import th.go.dxc.share.model.PageableDto;
import th.go.dxc.share.qm.api.controller.QmV2Api;
import th.go.dxc.share.qm.app.service.QmService;
@Api(description = "บริการค้นหาข้อมูล "+TsiPersonContact.METADATA_NAME+" "+TsiPersonContact.METADATA_ORG+" เวอร์ขั่น 2")
@RestController
@RequestMapping("/api/qm/v2/"+TsiPersonContact.METADATA_ORG_CODE+"/"+TsiPersonContact.METADATA_RESOURCES)
public class TsiPersonContactQmV2Api implements QmV2Api<TsiPersonContact, TsiPersonContactFilter>{

	private final QmService<TsiPersonContact, TsiPersonContactFilter> service;
	private final TsiPersonContactQmV2ApiMapper mapper;

	
	public TsiPersonContactQmV2Api(QmService<TsiPersonContact, TsiPersonContactFilter> service, TsiPersonContactQmV2ApiMapper mapper) {
		super();
		this.service = service;
		this.mapper = mapper;
	}

	@Override
	public PageDto<TsiPersonContact> findAll(TsiPersonContactFilter filterDto, PageableDto pageableDto)
			throws BadRequestException, BadGatewayException, InternalServerErrorException {
		PageDto<TsiPersonContact> resultPageDto = null;
		Pageable pageable =mapper.mapPageable(pageableDto); 
		Page<TsiPersonContact> resultPage = service.findAll(filterDto, pageable);
		resultPageDto = mapper.mapPageDto(resultPage);
		return resultPageDto;
	}

	@Override
	public TsiPersonContact findById(String id)
			throws BadRequestException, BadGatewayException, InternalServerErrorException, NotFoundException {
		return service.findById(id).orElseThrow(() -> new NotFoundException(TsiPersonContact.class.getName()+" with ID="+id+" not found."));
	}

}
