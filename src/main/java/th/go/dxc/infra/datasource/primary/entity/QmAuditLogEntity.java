package th.go.dxc.infra.datasource.primary.entity;

import java.time.OffsetDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Entity
@Data
public class QmAuditLogEntity {
	@Id
	private Long auditLogId;
	private OffsetDateTime startDateTime;
	private OffsetDateTime endDateTime;
	private String userName;
	private String userThaiNin;
	private String dataSetId;
	private String queryParameters;
	@Enumerated(EnumType.STRING)
	private HttpStatus resultStatus;
	private Integer resultSize;
	private String comment;
}
