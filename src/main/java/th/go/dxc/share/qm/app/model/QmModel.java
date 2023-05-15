package th.go.dxc.share.qm.app.model;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QmModel implements Serializable{	
	private static final long serialVersionUID = 1L;
	
	public final static String ID_FIELD_NAME = "id";
	@ApiModelProperty(value = "รหัสข้อมูล")
	@JacksonXmlProperty(isAttribute = true, localName = ID_FIELD_NAME)
	private String id;
}
