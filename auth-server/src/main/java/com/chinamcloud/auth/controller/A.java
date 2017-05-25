package com.chinamcloud.auth.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class A {

	@RequestMapping("/")
	public String index() {
		return "index";
	}

	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@GetMapping("/code")
	public void aaa(@RequestParam("code") String code) {

		// http://localhost:9999/uaa/oauth/authorize?response_type=code&client_id=acme&redirect_uri=http://localhost:9999/uaa/code

		System.err.println("code:" + code);

	}

}
