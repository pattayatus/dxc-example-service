package th.go.dxc.share.qm.api.dto;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "ข้อมูลการค้นหา")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QmV1QueryParameterDto {
	@ApiParam(value="เงื่อนไขการค้นหา",required=false,example="domain.field01=0000000000000")
	private String criteria;
	@ApiParam(value="ตำแหน่งเริ่มต้นข้อมูล (เริ่มที่ 0)",defaultValue="0",example="0")
	@Min(value=0,message="ตำแหน่งเริ่มต้นข้อมูลเริ่มต้นที่ 0")
	private Integer offset = 0;
	@ApiParam(value="จำนวนข้อมูลไม่เกิน (เริ่มที่ 1)",defaultValue="100",example = "100")
	@Min(value=1)
	private Integer maxNumberOfResults = 100;
	@ApiParam("เรียงลำดับโดย")
	private String orderBy;

}
