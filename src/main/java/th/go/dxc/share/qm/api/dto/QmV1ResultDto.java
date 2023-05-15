package th.go.dxc.share.qm.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(description = "ผลลัพท์การค้นหา")
@JacksonXmlRootElement(localName = "result")
@JsonPropertyOrder({ "header", "data"})
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QmV1ResultDto<T> implements Serializable {
		/**
	 * 
	 */
	private static final long serialVersionUID = 2701168312677871731L;
	/**
	 * 
	 */

	@ApiModelProperty("รายละเอียดการค้นหา")
	@JacksonXmlProperty(localName = "header")
	private QmV1ResultHeaderDto header = null;

//	@JacksonXmlElementWrapper(localName = "xx",useWrapping = true)
//	@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
	@ApiModelProperty("ผลการค้นหา")
	private List<T> data = new ArrayList<T>();

	



}
