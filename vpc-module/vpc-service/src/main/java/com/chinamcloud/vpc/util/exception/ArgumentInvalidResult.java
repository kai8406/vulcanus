package com.chinamcloud.vpc.util.exception;

import lombok.Data;

@Data
public class ArgumentInvalidResult {

	private String field;

	private String defaultMessage;

}
