package com.chinamcloud.vpc.util.exception;

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
	private String taskId;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	/**
	 * 创建默认服务端异常结果:500.
	 */
	public void setDefaultError(String taskId) {
		setRestResult(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Runtime unknown Internal Error.", taskId);
	}

	/**
	 * 创建成功结果.
	 */
	public void setSuccessResult(String taskId) {
		setRestResult(resultCode, resultMessage, taskId);
	}

	/**
	 * 创建成功结果.
	 * 
	 * @param data
	 */
	public void setSuccessResult(String taskId, T data) {
		setRestResult(resultCode, resultMessage, taskId, data);
	}

	/**
	 * 创建结果.
	 */
	public void setRestResult(Integer code, String message, String taskId) {
		resultCode = code;
		resultMessage = message;
		this.taskId = taskId;
	}

	/**
	 * 创建结果.
	 */
	public void setRestResult(Integer code, String message, String taskId, T t) {
		resultCode = code;
		resultMessage = message;
		this.taskId = taskId;
		data = t;
	}

}
