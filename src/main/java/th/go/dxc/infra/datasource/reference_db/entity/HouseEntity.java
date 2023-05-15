package th.go.dxc.infra.datasource.reference_db.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class HouseEntity {
	@Id
	private String jobCode;
	private String name;
}
