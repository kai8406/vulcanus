package com.chinamcloud.vpc.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Table(name = "vpc_service")
@Data
public class VPCVO {

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String id;

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

}
