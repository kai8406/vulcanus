package com.chinamcloud.vpc.entity;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class DeleteVpcRequest {

	@NotBlank
	private String access_token;

	@NotBlank
	private String callType = "api";

	@NotBlank
	private String platformId;

	@NotBlank
	private String vpcId;

}
