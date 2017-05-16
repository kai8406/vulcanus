package com.chinamcloud.vpc.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class UpdateVpcRequest {

	@NotBlank
	private String access_token;

	@NotBlank
	private String callType = "api";

	@NotBlank
	private String platformId;

	@NotBlank
	private String vpcId;

	@Length(min = 2, max = 128)
	private String vpcName;

	@Length(min = 2, max = 256)
	private String description;
}
