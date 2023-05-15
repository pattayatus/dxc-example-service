package th.go.dxc.infra.datasource.dxc_sam.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;

//id	4277452
//queryType	\N
//authenId	1712315a4910aadf3b6243907839r318465a4910aadf3b6
//userId	4438
//userCitizenId	\N
//userName	เธจเธฑเธเธ”เธดเนเธเธฑเธข เธ—เธณเธเธดเนเธเธฃ
//userDepartmentCode	16
//userIP	101.51.55.241
//queryTime	01:58.0
//requesterGlobalTransactionId	GID5a491806bef23559645586
//qmLogApp	\N
//queryString	http://10.14.34.66:40309/qm-api/doc/prisoners/5147483?offset=0&requesterGlobalTransactionId=GID5a491806bef23559645586&requesterLocalTransactionId=UID5a491806c2c14982057758&user_citizen=3440700023705&req_user_citizen=3440700023705
//xml	\N
//ownerDepartmentCode	12
//ownerDepartmentName	เธเธฃเธกเธฃเธฒเธเธ—เธฑเธ“เธ‘เน
//serverCode	Doc
//dataCode	prisoner
//responseTime	\N
//version	2.3.1-beta1
//requesterAccessResource	
//requesterAccessDateTime	1.51474E+12
//requesterLocalTransactionId	UID5a491806c2c14982057758
//requesterToken	\N
//responderTransactionId	9711b7d4-732a-4969-b859-32fac4481dd4
//criteria	
//orderBy	
//maxNumberOfResults	1
//offset	0
//numberOfResults	1
//totalPage	1
//cacheId	5a491806e8d7f
//responseCode	200
//responseMessage	Successful
//timestamp	01:59.1
//totalNumberOfResults	1
//clientRequestUri	\N
//clientRequestParams	\N
//qmErrorType	\N
//qmErrorDescription	\N
//qmErrorNote	\N
//environment	\N
//isFromCache	FALSE
//save_qmLogApp	\N
//qmErrorCode	\N
//qmErrorDetail	\N

/**
 * The persistent class for the DXC_QM_QUERY_LOG database table.
 * 
 */
@Entity
@Table(name="DXC_QM_QUERY_LOG")
public class DxcQmQueryLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;

	private String authenId;

	private String cacheId;

	private String clientRequestParams;

	private String clientRequestUri;

	private String criteria;

	private String dataCode;

	private String environment;

	private boolean isFromCache;

	private Integer maxNumberOfResults;

	private Integer numberOfResults;

	private Integer offset;

	private String orderBy;

	private String ownerDepartmentCode;

	private String ownerDepartmentName;

	private String qmErrorCode;

	private String qmErrorDescription;

	private String qmErrorDetail;

	private String qmErrorNote;

	private String qmErrorType;

	private String qmLogApp;

	private String queryString;

	private Timestamp queryTime;

	private String queryType;

	private String requesterAccessDateTime;

	private String requesterAccessResource;

	private String requesterGlobalTransactionId;

	private String requesterLocalTransactionId;

	private String requesterToken;

	private String responderTransactionId;

	private String responseCode;

	private String responseMessage;

	private Timestamp responseTime;

	private String save_qmLogApp;

	private String serverCode;

	private Timestamp timestamp;

	private Integer totalNumberOfResults;

	private Integer totalPage;

	private String userCitizenId;

	private String userDepartmentCode;

	private Integer userId;

	private String userIP;

	private String userName;

	private String version;

	private String xml;

	public DxcQmQueryLog() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAuthenId() {
		return this.authenId;
	}

	public void setAuthenId(String authenId) {
		this.authenId = authenId;
	}

	public String getCacheId() {
		return this.cacheId;
	}

	public void setCacheId(String cacheId) {
		this.cacheId = cacheId;
	}

	public String getClientRequestParams() {
		return this.clientRequestParams;
	}

	public void setClientRequestParams(String clientRequestParams) {
		this.clientRequestParams = clientRequestParams;
	}

	public String getClientRequestUri() {
		return this.clientRequestUri;
	}

	public void setClientRequestUri(String clientRequestUri) {
		this.clientRequestUri = clientRequestUri;
	}

	public String getCriteria() {
		return this.criteria;
	}

	public void setCriteria(String criteria) {
		this.criteria = criteria;
	}

	public String getDataCode() {
		return this.dataCode;
	}

	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public String getEnvironment() {
		return this.environment;
	}

	public void setEnvironment(String environment) {
		this.environment = environment;
	}

	public boolean getIsFromCache() {
		return this.isFromCache;
	}

	public void setIsFromCache(boolean isFromCache) {
		this.isFromCache = isFromCache;
	}

	public Integer getMaxNumberOfResults() {
		return this.maxNumberOfResults;
	}

	public void setMaxNumberOfResults(Integer maxNumberOfResults) {
		this.maxNumberOfResults = maxNumberOfResults;
	}

	public Integer getNumberOfResults() {
		return this.numberOfResults;
	}

	public void setNumberOfResults(Integer numberOfResults) {
		this.numberOfResults = numberOfResults;
	}

	public Integer getOffset() {
		return this.offset;
	}

	public void setOffset(Integer offset) {
		this.offset = offset;
	}

	public String getOrderBy() {
		return this.orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOwnerDepartmentCode() {
		return this.ownerDepartmentCode;
	}

	public void setOwnerDepartmentCode(String ownerDepartmentCode) {
		this.ownerDepartmentCode = ownerDepartmentCode;
	}

	public String getOwnerDepartmentName() {
		return this.ownerDepartmentName;
	}

	public void setOwnerDepartmentName(String ownerDepartmentName) {
		this.ownerDepartmentName = ownerDepartmentName;
	}

	public String getQmErrorCode() {
		return this.qmErrorCode;
	}

	public void setQmErrorCode(String qmErrorCode) {
		this.qmErrorCode = qmErrorCode;
	}

	public String getQmErrorDescription() {
		return this.qmErrorDescription;
	}

	public void setQmErrorDescription(String qmErrorDescription) {
		this.qmErrorDescription = qmErrorDescription;
	}

	public String getQmErrorDetail() {
		return this.qmErrorDetail;
	}

	public void setQmErrorDetail(String qmErrorDetail) {
		this.qmErrorDetail = qmErrorDetail;
	}

	public String getQmErrorNote() {
		return this.qmErrorNote;
	}

	public void setQmErrorNote(String qmErrorNote) {
		this.qmErrorNote = qmErrorNote;
	}

	public String getQmErrorType() {
		return this.qmErrorType;
	}

	public void setQmErrorType(String qmErrorType) {
		this.qmErrorType = qmErrorType;
	}

	public String getQmLogApp() {
		return this.qmLogApp;
	}

	public void setQmLogApp(String qmLogApp) {
		this.qmLogApp = qmLogApp;
	}

	public String getQueryString() {
		return this.queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public Timestamp getQueryTime() {
		return this.queryTime;
	}

	public void setQueryTime(Timestamp queryTime) {
		this.queryTime = queryTime;
	}

	public String getQueryType() {
		return this.queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public String getRequesterAccessDateTime() {
		return this.requesterAccessDateTime;
	}

	public void setRequesterAccessDateTime(String requesterAccessDateTime) {
		this.requesterAccessDateTime = requesterAccessDateTime;
	}

	public String getRequesterAccessResource() {
		return this.requesterAccessResource;
	}

	public void setRequesterAccessResource(String requesterAccessResource) {
		this.requesterAccessResource = requesterAccessResource;
	}

	public String getRequesterGlobalTransactionId() {
		return this.requesterGlobalTransactionId;
	}

	public void setRequesterGlobalTransactionId(String requesterGlobalTransactionId) {
		this.requesterGlobalTransactionId = requesterGlobalTransactionId;
	}

	public String getRequesterLocalTransactionId() {
		return this.requesterLocalTransactionId;
	}

	public void setRequesterLocalTransactionId(String requesterLocalTransactionId) {
		this.requesterLocalTransactionId = requesterLocalTransactionId;
	}

	public String getRequesterToken() {
		return this.requesterToken;
	}

	public void setRequesterToken(String requesterToken) {
		this.requesterToken = requesterToken;
	}

	public String getResponderTransactionId() {
		return this.responderTransactionId;
	}

	public void setResponderTransactionId(String responderTransactionId) {
		this.responderTransactionId = responderTransactionId;
	}

	public String getResponseCode() {
		return this.responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return this.responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public Timestamp getResponseTime() {
		return this.responseTime;
	}

	public void setResponseTime(Timestamp responseTime) {
		this.responseTime = responseTime;
	}

	public String getSave_qmLogApp() {
		return this.save_qmLogApp;
	}

	public void setSave_qmLogApp(String save_qmLogApp) {
		this.save_qmLogApp = save_qmLogApp;
	}

	public String getServerCode() {
		return this.serverCode;
	}

	public void setServerCode(String serverCode) {
		this.serverCode = serverCode;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getTotalNumberOfResults() {
		return this.totalNumberOfResults;
	}

	public void setTotalNumberOfResults(Integer totalNumberOfResults) {
		this.totalNumberOfResults = totalNumberOfResults;
	}

	public Integer getTotalPage() {
		return this.totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}

	public String getUserCitizenId() {
		return this.userCitizenId;
	}

	public void setUserCitizenId(String userCitizenId) {
		this.userCitizenId = userCitizenId;
	}

	public String getUserDepartmentCode() {
		return this.userDepartmentCode;
	}

	public void setUserDepartmentCode(String userDepartmentCode) {
		this.userDepartmentCode = userDepartmentCode;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserIP() {
		return this.userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getVersion() {
		return this.version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getXml() {
		return this.xml;
	}

	public void setXml(String xml) {
		this.xml = xml;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authenId == null) ? 0 : authenId.hashCode());
		result = prime * result + ((cacheId == null) ? 0 : cacheId.hashCode());
		result = prime * result + ((clientRequestParams == null) ? 0 : clientRequestParams.hashCode());
		result = prime * result + ((clientRequestUri == null) ? 0 : clientRequestUri.hashCode());
		result = prime * result + ((criteria == null) ? 0 : criteria.hashCode());
		result = prime * result + ((dataCode == null) ? 0 : dataCode.hashCode());
		result = prime * result + ((environment == null) ? 0 : environment.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (isFromCache ? 1231 : 1237);
		result = prime * result + ((maxNumberOfResults == null) ? 0 : maxNumberOfResults.hashCode());
		result = prime * result + ((numberOfResults == null) ? 0 : numberOfResults.hashCode());
		result = prime * result + ((offset == null) ? 0 : offset.hashCode());
		result = prime * result + ((orderBy == null) ? 0 : orderBy.hashCode());
		result = prime * result + ((ownerDepartmentCode == null) ? 0 : ownerDepartmentCode.hashCode());
		result = prime * result + ((ownerDepartmentName == null) ? 0 : ownerDepartmentName.hashCode());
		result = prime * result + ((qmErrorCode == null) ? 0 : qmErrorCode.hashCode());
		result = prime * result + ((qmErrorDescription == null) ? 0 : qmErrorDescription.hashCode());
		result = prime * result + ((qmErrorDetail == null) ? 0 : qmErrorDetail.hashCode());
		result = prime * result + ((qmErrorNote == null) ? 0 : qmErrorNote.hashCode());
		result = prime * result + ((qmErrorType == null) ? 0 : qmErrorType.hashCode());
		result = prime * result + ((qmLogApp == null) ? 0 : qmLogApp.hashCode());
		result = prime * result + ((queryString == null) ? 0 : queryString.hashCode());
		result = prime * result + ((queryTime == null) ? 0 : queryTime.hashCode());
		result = prime * result + ((queryType == null) ? 0 : queryType.hashCode());
		result = prime * result + ((requesterAccessDateTime == null) ? 0 : requesterAccessDateTime.hashCode());
		result = prime * result + ((requesterAccessResource == null) ? 0 : requesterAccessResource.hashCode());
		result = prime * result
				+ ((requesterGlobalTransactionId == null) ? 0 : requesterGlobalTransactionId.hashCode());
		result = prime * result + ((requesterLocalTransactionId == null) ? 0 : requesterLocalTransactionId.hashCode());
		result = prime * result + ((requesterToken == null) ? 0 : requesterToken.hashCode());
		result = prime * result + ((responderTransactionId == null) ? 0 : responderTransactionId.hashCode());
		result = prime * result + ((responseCode == null) ? 0 : responseCode.hashCode());
		result = prime * result + ((responseMessage == null) ? 0 : responseMessage.hashCode());
		result = prime * result + ((responseTime == null) ? 0 : responseTime.hashCode());
		result = prime * result + ((save_qmLogApp == null) ? 0 : save_qmLogApp.hashCode());
		result = prime * result + ((serverCode == null) ? 0 : serverCode.hashCode());
		result = prime * result + ((timestamp == null) ? 0 : timestamp.hashCode());
		result = prime * result + ((totalNumberOfResults == null) ? 0 : totalNumberOfResults.hashCode());
		result = prime * result + ((totalPage == null) ? 0 : totalPage.hashCode());
		result = prime * result + ((userCitizenId == null) ? 0 : userCitizenId.hashCode());
		result = prime * result + ((userDepartmentCode == null) ? 0 : userDepartmentCode.hashCode());
		result = prime * result + ((userIP == null) ? 0 : userIP.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result + ((userName == null) ? 0 : userName.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		result = prime * result + ((xml == null) ? 0 : xml.hashCode());
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
		DxcQmQueryLog other = (DxcQmQueryLog) obj;
		if (authenId == null) {
			if (other.authenId != null)
				return false;
		} else if (!authenId.equals(other.authenId))
			return false;
		if (cacheId == null) {
			if (other.cacheId != null)
				return false;
		} else if (!cacheId.equals(other.cacheId))
			return false;
		if (clientRequestParams == null) {
			if (other.clientRequestParams != null)
				return false;
		} else if (!clientRequestParams.equals(other.clientRequestParams))
			return false;
		if (clientRequestUri == null) {
			if (other.clientRequestUri != null)
				return false;
		} else if (!clientRequestUri.equals(other.clientRequestUri))
			return false;
		if (criteria == null) {
			if (other.criteria != null)
				return false;
		} else if (!criteria.equals(other.criteria))
			return false;
		if (dataCode == null) {
			if (other.dataCode != null)
				return false;
		} else if (!dataCode.equals(other.dataCode))
			return false;
		if (environment == null) {
			if (other.environment != null)
				return false;
		} else if (!environment.equals(other.environment))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (isFromCache != other.isFromCache)
			return false;
		if (maxNumberOfResults == null) {
			if (other.maxNumberOfResults != null)
				return false;
		} else if (!maxNumberOfResults.equals(other.maxNumberOfResults))
			return false;
		if (numberOfResults == null) {
			if (other.numberOfResults != null)
				return false;
		} else if (!numberOfResults.equals(other.numberOfResults))
			return false;
		if (offset == null) {
			if (other.offset != null)
				return false;
		} else if (!offset.equals(other.offset))
			return false;
		if (orderBy == null) {
			if (other.orderBy != null)
				return false;
		} else if (!orderBy.equals(other.orderBy))
			return false;
		if (ownerDepartmentCode == null) {
			if (other.ownerDepartmentCode != null)
				return false;
		} else if (!ownerDepartmentCode.equals(other.ownerDepartmentCode))
			return false;
		if (ownerDepartmentName == null) {
			if (other.ownerDepartmentName != null)
				return false;
		} else if (!ownerDepartmentName.equals(other.ownerDepartmentName))
			return false;
		if (qmErrorCode == null) {
			if (other.qmErrorCode != null)
				return false;
		} else if (!qmErrorCode.equals(other.qmErrorCode))
			return false;
		if (qmErrorDescription == null) {
			if (other.qmErrorDescription != null)
				return false;
		} else if (!qmErrorDescription.equals(other.qmErrorDescription))
			return false;
		if (qmErrorDetail == null) {
			if (other.qmErrorDetail != null)
				return false;
		} else if (!qmErrorDetail.equals(other.qmErrorDetail))
			return false;
		if (qmErrorNote == null) {
			if (other.qmErrorNote != null)
				return false;
		} else if (!qmErrorNote.equals(other.qmErrorNote))
			return false;
		if (qmErrorType == null) {
			if (other.qmErrorType != null)
				return false;
		} else if (!qmErrorType.equals(other.qmErrorType))
			return false;
		if (qmLogApp == null) {
			if (other.qmLogApp != null)
				return false;
		} else if (!qmLogApp.equals(other.qmLogApp))
			return false;
		if (queryString == null) {
			if (other.queryString != null)
				return false;
		} else if (!queryString.equals(other.queryString))
			return false;
		if (queryTime == null) {
			if (other.queryTime != null)
				return false;
		} else if (!queryTime.equals(other.queryTime))
			return false;
		if (queryType == null) {
			if (other.queryType != null)
				return false;
		} else if (!queryType.equals(other.queryType))
			return false;
		if (requesterAccessDateTime == null) {
			if (other.requesterAccessDateTime != null)
				return false;
		} else if (!requesterAccessDateTime.equals(other.requesterAccessDateTime))
			return false;
		if (requesterAccessResource == null) {
			if (other.requesterAccessResource != null)
				return false;
		} else if (!requesterAccessResource.equals(other.requesterAccessResource))
			return false;
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
		if (responderTransactionId == null) {
			if (other.responderTransactionId != null)
				return false;
		} else if (!responderTransactionId.equals(other.responderTransactionId))
			return false;
		if (responseCode == null) {
			if (other.responseCode != null)
				return false;
		} else if (!responseCode.equals(other.responseCode))
			return false;
		if (responseMessage == null) {
			if (other.responseMessage != null)
				return false;
		} else if (!responseMessage.equals(other.responseMessage))
			return false;
		if (responseTime == null) {
			if (other.responseTime != null)
				return false;
		} else if (!responseTime.equals(other.responseTime))
			return false;
		if (save_qmLogApp == null) {
			if (other.save_qmLogApp != null)
				return false;
		} else if (!save_qmLogApp.equals(other.save_qmLogApp))
			return false;
		if (serverCode == null) {
			if (other.serverCode != null)
				return false;
		} else if (!serverCode.equals(other.serverCode))
			return false;
		if (timestamp == null) {
			if (other.timestamp != null)
				return false;
		} else if (!timestamp.equals(other.timestamp))
			return false;
		if (totalNumberOfResults == null) {
			if (other.totalNumberOfResults != null)
				return false;
		} else if (!totalNumberOfResults.equals(other.totalNumberOfResults))
			return false;
		if (totalPage == null) {
			if (other.totalPage != null)
				return false;
		} else if (!totalPage.equals(other.totalPage))
			return false;
		if (userCitizenId == null) {
			if (other.userCitizenId != null)
				return false;
		} else if (!userCitizenId.equals(other.userCitizenId))
			return false;
		if (userDepartmentCode == null) {
			if (other.userDepartmentCode != null)
				return false;
		} else if (!userDepartmentCode.equals(other.userDepartmentCode))
			return false;
		if (userIP == null) {
			if (other.userIP != null)
				return false;
		} else if (!userIP.equals(other.userIP))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		if (xml == null) {
			if (other.xml != null)
				return false;
		} else if (!xml.equals(other.xml))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DxcQmQueryLog [id=" + id + ", authenId=" + authenId + ", cacheId=" + cacheId + ", clientRequestParams="
				+ clientRequestParams + ", clientRequestUri=" + clientRequestUri + ", criteria=" + criteria
				+ ", dataCode=" + dataCode + ", environment=" + environment + ", isFromCache=" + isFromCache
				+ ", maxNumberOfResults=" + maxNumberOfResults + ", numberOfResults=" + numberOfResults + ", offset="
				+ offset + ", orderBy=" + orderBy + ", ownerDepartmentCode=" + ownerDepartmentCode
				+ ", ownerDepartmentName=" + ownerDepartmentName + ", qmErrorCode=" + qmErrorCode
				+ ", qmErrorDescription=" + qmErrorDescription + ", qmErrorDetail=" + qmErrorDetail + ", qmErrorNote="
				+ qmErrorNote + ", qmErrorType=" + qmErrorType + ", qmLogApp=" + qmLogApp + ", queryString="
				+ queryString + ", queryTime=" + queryTime + ", queryType=" + queryType + ", requesterAccessDateTime="
				+ requesterAccessDateTime + ", requesterAccessResource=" + requesterAccessResource
				+ ", requesterGlobalTransactionId=" + requesterGlobalTransactionId + ", requesterLocalTransactionId="
				+ requesterLocalTransactionId + ", requesterToken=" + requesterToken + ", responderTransactionId="
				+ responderTransactionId + ", responseCode=" + responseCode + ", responseMessage=" + responseMessage
				+ ", responseTime=" + responseTime + ", save_qmLogApp=" + save_qmLogApp + ", serverCode=" + serverCode
				+ ", timestamp=" + timestamp + ", totalNumberOfResults=" + totalNumberOfResults + ", totalPage="
				+ totalPage + ", userCitizenId=" + userCitizenId + ", userDepartmentCode=" + userDepartmentCode
				+ ", userId=" + userId + ", userIP=" + userIP + ", userName=" + userName + ", version=" + version
				+ ", xml=" + xml + "]";
	}

	
}