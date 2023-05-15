package th.go.dxc.share.qm.api.dto;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiParam;

@ApiModel(description = "QM V1 ค่าความปลอดภัย")
//@Schema(description = "QM V1 ค่าความปลอดภัย")
public class QmV1SecurityParameterDto {
	
	@ApiParam("เลขประจำตัวประชาชนผู้เรียกดูข้อมูล")
//	@Schema(description = "เลขประจำตัวประชาชนผู้เรียกดูข้อมูล")
	@NotNull(message="กรุณาระบุเลขประจำตัวประชาชนผู้เรียกดูข้อมูล")
	private String userCitizenNumber;
	@ApiParam("รหัสงานหลักผู้เรียกข้อมูล")
//	@Schema(description = "รหัสงานหลักผู้เรียกข้อมูล")
	private String requesterGlobalTransactionId;
	@ApiParam("รหัสงานรองผู้เรียกข้อมูล")
//	@Schema(description = "รหัสงานรองผู้เรียกข้อมูล")
	private String requesterLocalTransactionId;
	@ApiParam("รหัสผู้เรียกข้อมูล")
//	@Schema(description = "รหัสผู้เรียกข้อมูล")
	private String requesterToken;

	public QmV1SecurityParameterDto() {
		
	}

	public QmV1SecurityParameterDto(String userCitizenNumber, String requesterGlobalTransactionId,
			String requesterLocalTransactionId, String requesterToken) {
		super();
		this.userCitizenNumber = userCitizenNumber;
		this.requesterGlobalTransactionId = requesterGlobalTransactionId;
		this.requesterLocalTransactionId = requesterLocalTransactionId;
		this.requesterToken = requesterToken;
	}

	public String getUserCitizenNumber() {
		return userCitizenNumber;
	}

	public void setUserCitizenNumber(String userCitizenNumber) {
		if(userCitizenNumber !=null)
		{
			// clear qoute character
			userCitizenNumber.replaceAll("'", "");
		}
		this.userCitizenNumber = userCitizenNumber;
	}

	public String getRequesterGlobalTransactionId() {
		return requesterGlobalTransactionId;
	}

	public void setRequesterGlobalTransactionId(String requesterGlobalTransactionId) {
		this.requesterGlobalTransactionId = requesterGlobalTransactionId;
	}

	public String getRequesterLocalTransactionId() {
		return requesterLocalTransactionId;
	}

	public void setRequesterLocalTransactionId(String requesterLocalTransactionId) {
		this.requesterLocalTransactionId = requesterLocalTransactionId;
	}

	public String getRequesterToken() {
		return requesterToken;
	}

	public void setRequesterToken(String requesterToken) {
		this.requesterToken = requesterToken;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((requesterGlobalTransactionId == null) ? 0 : requesterGlobalTransactionId.hashCode());
		result = prime * result + ((requesterLocalTransactionId == null) ? 0 : requesterLocalTransactionId.hashCode());
		result = prime * result + ((requesterToken == null) ? 0 : requesterToken.hashCode());
		result = prime * result + ((userCitizenNumber == null) ? 0 : userCitizenNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		QmV1SecurityParameterDto other = (QmV1SecurityParameterDto) obj;
		if (requesterGlobalTransactionId == null) {
			if (other.requesterGlobalTransactionId != null)
				return false;
		} else if (!requesterGlobalTransactionId.equals(other.requesterGlobalTransactionId))
			return false;
		if (requesterLocalTransactionId == null) {
			if (other.requesterLocalTransactionId != null)
				return false;
		} else if (!requesterLocalTransactionId.equals(other.requesterLocalTransactionId))
			return false;
		if (requesterToken == null) {
			if (other.requesterToken != null)
				return false;
		} else if (!requesterToken.equals(other.requesterToken))
			return false;
		if (userCitizenNumber == null) {
			if (other.userCitizenNumber != null)
				return false;
		} else if (!userCitizenNumber.equals(other.userCitizenNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "QmV1RequestSecurityParameterDto [userCitizenNumber=" + userCitizenNumber
				+ ", requesterGlobalTransactionId=" + requesterGlobalTransactionId + ", requesterLocalTransactionId="
				+ requesterLocalTransactionId + ", requesterToken=" + requesterToken + "]";
	}

	
}
