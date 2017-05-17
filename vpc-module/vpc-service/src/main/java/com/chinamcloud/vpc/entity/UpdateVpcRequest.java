package com.chinamcloud.vpc.entity;

import org.hibernate.validator.constraints.Length;

import lombok.Data;

@Data
public class UpdateVpcRequest {

	@Length(min = 2, max = 128)
	private String vpcName;

	@Length(min = 2, max = 256)
	private String description;
}
