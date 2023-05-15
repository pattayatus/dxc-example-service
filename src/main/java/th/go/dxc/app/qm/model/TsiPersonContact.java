package th.go.dxc.app.qm.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import th.go.dxc.share.qm.app.model.QmModel;
@ApiModel(description = "ข้อมูล"+TsiPersonContact.METADATA_NAME+" "+TsiPersonContact.METADATA_ORG)
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper=false)
public class TsiPersonContact extends QmModel{
	private static final long serialVersionUID = 1L;
	
	public static final String METADATA_ORG_CODE="tsi";
	public static final String METADATA_DATA_CODE="person";
	public static final String METADATA_NAME="คน";
	public static final String METADATA_ORG="TSI";
	public static final String METADATA_RESOURCE="person-contact";
	public static final String METADATA_RESOURCES="person-contacts";
	public static final String METADATA_XML_NAME="personContact";
	public static final String DATA_SET_ID = METADATA_ORG_CODE+"-"+METADATA_DATA_CODE;
	
	@ApiModelProperty("รหัสบุคคล") 
	private Long personId;
	
	@ApiModelProperty("ชื่อ") 
	private String givenName;
	
	@ApiModelProperty("นามสกุล") 
	private String surName;
}
