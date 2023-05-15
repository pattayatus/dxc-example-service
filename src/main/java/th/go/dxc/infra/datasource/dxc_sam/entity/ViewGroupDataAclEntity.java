package th.go.dxc.infra.datasource.dxc_sam.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * The persistent class for the VIEW_GROUP_DATA_ACL database table.
 * 
 */
@Entity
@Table(name="VIEW_GROUP_DATA_ACL",catalog="SAM",schema="DBO")
public class ViewGroupDataAclEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name="allow_value")
	private String allowValue;

	@Column(name="dataset_id")
	private String datasetId;

	@Column(name="dataset_organization_id")
	private String datasetOrganizationId;

	@Id
	private Long id;

	private String tag;

	@Column(name="user_citizen_card_number")
	private String userCitizenCardNumber;

	@Column(name="user_group_id")
	private Long userGroupId;

	@Column(name="user_group_name")
	private String userGroupName;

	@Column(name="user_organization_id")
	private String userOrganizationId;

	@Column(name="user_username")
	private String userUsername;

	public ViewGroupDataAclEntity() {
	}

	public String getAllowValue() {
		return this.allowValue;
	}

	public void setAllowValue(String allowValue) {
		this.allowValue = allowValue;
	}

	public String getDatasetId() {
		return this.datasetId;
	}

	public void setDatasetId(String datasetId) {
		this.datasetId = datasetId;
	}

	public String getDatasetOrganizationId() {
		return this.datasetOrganizationId;
	}

	public void setDatasetOrganizationId(String datasetOrganizationId) {
		this.datasetOrganizationId = datasetOrganizationId;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTag() {
		return this.tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getUserCitizenCardNumber() {
		return this.userCitizenCardNumber;
	}

	public void setUserCitizenCardNumber(String userCitizenCardNumber) {
		this.userCitizenCardNumber = userCitizenCardNumber;
	}

	public Long getUserGroupId() {
		return this.userGroupId;
	}

	public void setUserGroupId(Long userGroupId) {
		this.userGroupId = userGroupId;
	}

	public String getUserGroupName() {
		return this.userGroupName;
	}

	public void setUserGroupName(String userGroupName) {
		this.userGroupName = userGroupName;
	}

	public String getUserOrganizationId() {
		return this.userOrganizationId;
	}

	public void setUserOrganizationId(String userOrganizationId) {
		this.userOrganizationId = userOrganizationId;
	}

	public String getUserUsername() {
		return this.userUsername;
	}

	public void setUserUsername(String userUsername) {
		this.userUsername = userUsername;
	}

}