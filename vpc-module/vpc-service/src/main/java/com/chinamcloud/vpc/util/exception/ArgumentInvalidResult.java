package com.chinamcloud.vpc.util.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
public class ArgumentInvalidResult {

	private String field;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private Object rejectedValue;

	private String defaultMessage;

}
