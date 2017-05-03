package com.chinamcloud.vpc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vpc_service")
public class VPCVO {

	@Id
	@GeneratedValue
	private Integer id;

	@Column(name = "cidr")
	private String cidr;

	@Column(name = "description")
	private String description;

	@Column(name = "platform_id")
	private String platformId;

	@Column(name = "region_id")
	private String regionId;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "vpc_code")
	private String vpcCode;

	@Column(name = "vpc_name")
	private String vpcName;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCidr() {
		return cidr;
	}

	public void setCidr(String cidr) {
		this.cidr = cidr;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPlatformId() {
		return platformId;
	}

	public void setPlatformId(String platformId) {
		this.platformId = platformId;
	}

	public String getRegionId() {
		return regionId;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getVpcCode() {
		return vpcCode;
	}

	public void setVpcCode(String vpcCode) {
		this.vpcCode = vpcCode;
	}

	public String getVpcName() {
		return vpcName;
	}

	public void setVpcName(String vpcName) {
		this.vpcName = vpcName;
	}

}
