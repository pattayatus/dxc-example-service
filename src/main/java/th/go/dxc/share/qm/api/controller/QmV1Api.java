package th.go.dxc.share.qm.api.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import th.go.dxc.share.exception.BadGatewayException;
import th.go.dxc.share.exception.BadRequestException;
import th.go.dxc.share.exception.InternalServerErrorException;
import th.go.dxc.share.model.ErrorDto;
import th.go.dxc.share.qm.api.dto.QmV1QueryParameterDto;
import th.go.dxc.share.qm.api.dto.QmV1ResultDto;
import th.go.dxc.share.qm.api.dto.QmV1SecurityParameterDto;

public interface QmV1Api<DTO> {
	/**
	 * 
	 * @param securityParamDto
	 * @param queryParamDto
	 * @param request
	 * @return
	 */
	@GetMapping(produces = { MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "ค้นหาข้อมูลด้วยเงื่อนไข", notes = "บริการค้นหาข้อมูลด้วยเงื่อนไข แบบ Criteria (JPA QL)")
	@ApiResponses(
			{ 
				@ApiResponse(code = 400, message = "ข้อมูลคำขอที่ส่งมาไม่ถูกต้อง", response = ErrorDto.class),
				@ApiResponse(code = 401, message = "ไม่มีสิทธิในการใช้งาน", response = ErrorDto.class),
				@ApiResponse(code = 404, message = "ไม่พบข้อมูล", response = ErrorDto.class),
				@ApiResponse(code = 500, message = "ระบบมีปัญหา", response = ErrorDto.class)
			})
	public QmV1ResultDto<DTO> findByCriteria(  
			@Valid QmV1SecurityParameterDto securityParamDto,
			@Valid QmV1QueryParameterDto queryParameterDto, HttpServletRequest request) throws BadRequestException, BadGatewayException, InternalServerErrorException;

	/**
	 * 
	 * @param dataId
	 * @param request
	 * @param securityParamDto
	 * @return
	 */
	@GetMapping(path = "/{dtoId}", produces = { MediaType.APPLICATION_XML_VALUE })
	@ApiOperation(value = "ค้นหาข้อมูลด้วยรหัสข้อมูล", notes = "บริการค้นหาข้อมูล ด้วย รหัสข้อมูล")
	@ApiResponses({ @ApiResponse(code = 400, message = "ข้อมูลคำขอที่ส่งมาไม่ถูกต้อง", response = ErrorDto.class),
			@ApiResponse(code = 401, message = "ไม่มีสิทธิในการใช้งาน", response = ErrorDto.class),
			@ApiResponse(code = 404, message = "ไม่พบข้อมูล", response = ErrorDto.class),
			@ApiResponse(code = 500, message = "ระบบมีปัญหา", response = ErrorDto.class) })
	public QmV1ResultDto<DTO> getById(@PathVariable("dtoId") String dtoId, HttpServletRequest request,
			@Valid QmV1SecurityParameterDto securityParamDto) throws BadRequestException, BadGatewayException, InternalServerErrorException;
}
