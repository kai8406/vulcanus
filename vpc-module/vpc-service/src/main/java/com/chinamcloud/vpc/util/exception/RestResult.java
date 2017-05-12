package com.chinamcloud.vpc.util.exception;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResult<T> {

	private Integer resultCode;
	private String resultMessage;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

}
