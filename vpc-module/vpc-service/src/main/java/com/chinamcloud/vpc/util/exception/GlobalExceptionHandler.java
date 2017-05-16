package com.chinamcloud.vpc.util.exception;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@ResponseBody
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(value = RuntimeException.class)
	private <T> RestResult<T> runtimeExceptionHandler(RuntimeException exception) {

		if (log.isDebugEnabled()) {
			log.debug(exception.getLocalizedMessage());
		}

		return new RestResult<T>(HttpStatus.INTERNAL_SERVER_ERROR.value(),
				HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
	}

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public Object MethodArgumentNotValidHandler(HttpServletRequest request, MethodArgumentNotValidException exception) {

		List<ArgumentInvalidResult> list = new ArrayList<>();

		// 解析原错误信息，封装后返回，此处返回非法的字段名称，原始值，错误信息
		for (FieldError error : exception.getBindingResult().getFieldErrors()) {

			ArgumentInvalidResult invalidArgument = new ArgumentInvalidResult();

			invalidArgument.setDefaultMessage(error.getDefaultMessage());
			invalidArgument.setField(error.getField());
			list.add(invalidArgument);
		}

		RestResult<List<ArgumentInvalidResult>> result = new RestResult<List<ArgumentInvalidResult>>(
				HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.getReasonPhrase(), list);

		return result;
	}

}
