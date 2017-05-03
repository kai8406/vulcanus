package com.chinamcloud.auth.controller;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chinamcloud.auth.domain.User;
import com.chinamcloud.auth.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping("/current")
	public Principal getUser(Principal principal) {
		System.out.println(principal.getName());
		return principal;
	}

	@PreAuthorize("#oauth2.hasScope('server')")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public void createUser(@Valid @RequestBody User user) {
		service.create(user);
	}

	@PreAuthorize("#oauth2.hasScope('server')")
	@GetMapping
	public User getUser(@RequestParam(value = "username") String username) {
		return service.find(username);
	}

}
