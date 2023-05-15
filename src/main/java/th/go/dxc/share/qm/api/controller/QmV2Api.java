package th.go.dxc.share.qm.api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import th.go.dxc.share.exception.BadGatewayException;
import th.go.dxc.share.exception.BadRequestException;
import th.go.dxc.share.exception.InternalServerErrorException;
import th.go.dxc.share.exception.NotFoundException;
import th.go.dxc.share.model.ErrorDto;
import th.go.dxc.share.model.PageDto;
import th.go.dxc.share.model.PageableDto;

public interface QmV2Api<DTO,FILTER_DTO> {
	
	@ApiOperation(value="ค้นหาข้อมูลตามเงื่อนไข",notes = "บริการค้นหาข้อมูลตามเงื่อนไข กรองข้อมูล")
	@ApiResponses({ @ApiResponse(code = 400, message = "ข้อมูลคำขอที่ส่งมาไม่ถูกต้อง", response = ErrorDto.class),
		@ApiResponse(code = 401, message = "ไม่มีสิทธิในการใช้งาน", response = ErrorDto.class),
		@ApiResponse(code = 404, message = "ไม่พบข้อมูล", response = ErrorDto.class),
		@ApiResponse(code = 500, message = "ระบบมีปัญหา", response = ErrorDto.class) })
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE })
	public PageDto<DTO> findAll(FILTER_DTO filterDto,PageableDto pageableDto) throws BadRequestException, BadGatewayException, InternalServerErrorException;

	@ApiOperation(value="ค้นหาจากรหัสข้อมูล",notes = "บริการค้นหาข้อมูลจากรหัสข้อมูล")
	@ApiResponses({ @ApiResponse(code = 400, message = "ข้อมูลคำขอที่ส่งมาไม่ถูกต้อง", response = ErrorDto.class),
		@ApiResponse(code = 401, message = "ไม่มีสิทธิในการใช้งาน", response = ErrorDto.class),
		@ApiResponse(code = 404, message = "ไม่พบข้อมูล", response = ErrorDto.class),
		@ApiResponse(code = 500, message = "ระบบมีปัญหา", response = ErrorDto.class) })
	@GetMapping(value="/{id}",produces = { MediaType.APPLICATION_JSON_VALUE })
	public DTO findById(@PathVariable("id") String id)throws BadRequestException, BadGatewayException, InternalServerErrorException,NotFoundException;
}
