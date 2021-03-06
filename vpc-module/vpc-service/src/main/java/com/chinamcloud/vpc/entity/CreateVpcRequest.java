package com.chinamcloud.vpc.entity;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class CreateVpcRequest {

	@NotBlank
	private String platformId;

	@NotBlank
	private String regionId;

	@NotBlank
	private String cidrBlock;

	@Length(min = 2, max = 128)
	private String vpcName;

	@Length(min = 2, max = 256)
	private String description;

}
