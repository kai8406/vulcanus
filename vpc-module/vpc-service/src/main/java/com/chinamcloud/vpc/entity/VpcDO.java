package com.chinamcloud.vpc.entity;

import java.util.Date;

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
public class VpcDO {

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String id;

	@Column(name = "user_id")
	private String userId;

	@Column(name = "call_type")
	private String callType;

	@Column(name = "platform_id")
	private String platformId;

	@Column(name = "task_id")
	private String task_id;

	@Column(name = "region_id")
	private String regionId;

	@Column(name = "cidr_block")
	private String cidrBlock;

	@Column(name = "description")
	private String description;

	@Column(name = "vpc_name")
	private String vpcName;

	@Column(name = "status")
	private String status;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "create_time")
	private Date modifyTime;

	@Column(name = "is_available")
	private boolean available = true;

}
