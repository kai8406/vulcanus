package com.chinamcloud.auth.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chinamcloud.auth.domain.User;
import com.chinamcloud.auth.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/user")
	public Principal getUser(Principal principal) {
		return principal;
	}

	@GetMapping("/name")
	public String getUserName(OAuth2Authentication auth) {
		return auth.getName();
	}

	// @PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public void createUser(@Valid @RequestBody User user) {
		service.create(user);
	}

}
