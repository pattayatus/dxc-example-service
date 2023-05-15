package th.go.dxc.share.qm.api.dto;

import java.io.Serializable;
import java.time.OffsetDateTime;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@JacksonXmlRootElement(localName = "header")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "head", propOrder = { "requesterAccessResource", "requesterAccessDateTime",
		"requesterGlobalTransactionId", "requesterLocalTransactionId", "requesterUsername", "requesterIpAddress",
		"requesterToken", "responderTransactionId", "responderResponseDateTime", "criteria", "orderBy",
		"maxNumberOfResults", "offset", "numberOfResults", "totalNumberOfResults" })
@ApiModel(description="ข้อมูลการค้นหา")
@Data
public class QmV1ResultHeaderDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4552578752914337838L;

	private String requesterAccessResource;
	private OffsetDateTime requesterAccessDateTime;
	private String requesterGlobalTransactionId;
	private String requesterLocalTransactionId;
	private String requesterUsername;
	private String requesterIpAddress;
	private String requesterToken;
	private String responderTransactionId;
	private OffsetDateTime responderResponseDateTime;
	private String criteria;
	private String orderBy;
	private Integer maxNumberOfResults;
	private Integer offset;
	private Integer numberOfResults;
	private Long totalNumberOfResults;
}
