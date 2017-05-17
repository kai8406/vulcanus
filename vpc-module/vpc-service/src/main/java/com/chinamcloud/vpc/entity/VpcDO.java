package com.chinamcloud.vpc.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "vpc_service")
@Data
public class VpcDO {

	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid")
	@GeneratedValue(generator = "system-uuid")
	private String id;

	/**
	 * 用户ID.
	 */
	@Column(name = "user_id")
	private String userId;

	/**
	 * 调用方式. api/platform/sync
	 */
	@Column(name = "call_type")
	private String callType;

	/**
	 * 平台ID. aws/aliyun/华栖云
	 */
	@Column(name = "platform_id")
	private String platformId;

	/**
	 * task对象,不持久化.
	 */
	@Transient
	@JsonIgnore
	private String taskId;

	/**
	 * 数据状态, A:活跃 N:不活跃,用于显示数据.
	 */
	@JsonIgnore
	@Column(name = "active")
	private String active = "A";

	/**
	 * 平台资源的唯一标识符
	 */
	@Column(name = "uuid")
	private String uuid = "";

	/**
	 * 资源状态.
	 */
	@Column(name = "status")
	private String status = "";

	/**
	 * 区域
	 */
	@Column(name = "region_id")
	private String regionId;

	/**
	 * CIDR
	 */
	@Column(name = "cidr_block")
	private String cidrBlock;

	/**
	 * 说明
	 */
	@Column(name = "description")
	private String description = "";

	/**
	 * VPC名称
	 */

	@Column(name = "vpc_name")
	private String vpcName = "";

	/**
	 * 创建时间
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "create_time")
	private Date createTime;

	/**
	 * 修改时间
	 */
	@JsonIgnore
	@Column(name = "modify_time")
	private Date modifyTime;

}
