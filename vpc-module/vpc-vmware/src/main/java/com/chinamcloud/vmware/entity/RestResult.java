package com.chinamcloud.vmware.entity;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResult<T> {

	private Integer resultCode = HttpStatus.OK.value();
	private String resultMessage = HttpStatus.OK.getReasonPhrase();

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	/**
	 * 创建默认服务端异常结果:500.
	 */
	public void setDefaultError() {
		setRestResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Runtime unknown Internal Error.");
	}

	/**
	 * 创建成功结果.
	 */
	public void setSuccessResult() {
		setRestResult(resultCode, resultMessage);
	}

	/**
	 * 创建成功结果.
	 * 
	 * @param data
	 */
	public void setSuccessResult(T data) {
		setRestResult(resultCode, resultMessage, data);
	}

	/**
	 * 创建结果.
	 */
	public void setRestResult(Integer code, String message) {
		resultCode = code;
		resultMessage = message;
	}

	/**
	 * 创建结果.
	 */
	public void setRestResult(Integer code, String message, T t) {
		resultCode = code;
		resultMessage = message;
		data = t;
	}

}
