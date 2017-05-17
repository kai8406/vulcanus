package com.chinamcloud.vpc.entity;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class DeleteVpcRequest {

	@NotBlank
	private String access_token;


}
