package th.go.dxc.share.model;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class Auditable {
	private OffsetDateTime createDate;
	private String createBy;
	private OffsetDateTime lastModifiedDate;
	private String lastModifiedBy;
}
