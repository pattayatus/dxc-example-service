package th.go.dxc.share.model;

import java.util.List;

import org.springframework.data.domain.Sort.Direction;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="pageable", description = "ข้อมูลการแบ่งหน้าข้อมูล")
public class PageableDto {
	@ApiModelProperty(value="เลขหน้า (เริ่มที่ 0)",example="0")
	private Integer page;
	@ApiModelProperty(value="จำนวนรายการต่อหน้า (เริ่มที่ 1)",example="10")
	private Integer size;
	@ApiModelProperty(value="ชื่อฟิลด์ที่ต้องการเรียง คั่นด้วยลูกน้ำ (,)",example="propA,propB,propC")
	private List<String> sortProperties;
	@ApiModelProperty(value="ทิศทางการเรียง ASC = น้อยไปมาก, DESC = มากไปน้อย",example="ASC")
	private Direction sortDirection;
}
