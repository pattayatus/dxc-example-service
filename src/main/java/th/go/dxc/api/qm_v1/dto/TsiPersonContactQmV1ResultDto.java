package th.go.dxc.api.qm_v1.dto;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import th.go.dxc.app.qm.model.TsiPersonContact;
import th.go.dxc.share.qm.api.dto.QmV1ResultDto;

public class TsiPersonContactQmV1ResultDto extends QmV1ResultDto<TsiPersonContact>{
	private static final long serialVersionUID = 1188899443831316257L;
	
	@Override
	@JacksonXmlElementWrapper(localName="data",useWrapping=true)
	@JacksonXmlProperty(localName=TsiPersonContact.METADATA_XML_NAME)
	public void setData(List<TsiPersonContact> data) {
		super.setData(data);
	}
}
