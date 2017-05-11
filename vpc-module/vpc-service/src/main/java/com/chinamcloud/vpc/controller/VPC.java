package com.chinamcloud.vpc.controller;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class VPC {

	@NotBlank(message = "access_token不能为空.")
	private String access_token;

	@NotBlank
	private String callType = "api";

	private String platformId;

	@NotBlank
	private String regionId;

	@NotBlank
	private String cidrBlock;

	private String userCidr;

	@Length(max = 28)
	private String vpcName;

	@Length(max = 28)
	private String description;

}
