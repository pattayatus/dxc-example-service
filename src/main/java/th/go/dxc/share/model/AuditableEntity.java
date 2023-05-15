package th.go.dxc.share.model;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Data
public class AuditableEntity implements Serializable{

	private static final long serialVersionUID = 408546624571106099L;

	@Column(name = "created_date", nullable = false, updatable = false)
	@CreatedDate
	private OffsetDateTime createDate;

	@Column(name = "created_by", nullable = false, updatable = false)
	@CreatedBy
	private String createBy;
	
	@Column(name = "last_modified_date")
	@LastModifiedDate
	private OffsetDateTime lastModifiedDate;
	
	@Column(name = "last_modified_by")
	@LastModifiedBy
	private String lastModifiedBy;
}
