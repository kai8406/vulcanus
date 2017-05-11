package com.chinamcloud.vpc.repository;

import org.junit.Test;
import org.springframework.http.HttpStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class T {

	@Test
	public void tewt() {
		System.out.println(HttpStatus.OK);
		log.info(HttpStatus.OK.getReasonPhrase());
	}
}
