package th.go.dxc.infra.datasource.data_db.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TsiPersonContactEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long personId;
	private String givenName;
	private String surName;
}
