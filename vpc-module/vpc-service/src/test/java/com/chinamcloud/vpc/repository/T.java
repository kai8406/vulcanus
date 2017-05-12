package com.chinamcloud.vpc.repository;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import com.chinamcloud.vpc.business.TaskStatusEnum;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class T {

	@Test
	public void tewt() {
		System.out.println(TaskStatusEnum.执行中.getClass());
		log.info(HttpStatus.OK.getReasonPhrase());
		
		
	}
}
