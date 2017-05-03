package com.chinamcloud.auth.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.chinamcloud.auth.domain.User;
import com.chinamcloud.auth.repository.UserRepository;
import com.chinamcloud.auth.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository repository;

	@Override
	public void create(User user) {

		User existing = repository.findByUsername(user.getUsername());
		Assert.isNull(existing, "user already exists: " + user.getUsername());

		String hash = encoder.encode(user.getPassword());

		user.setPassword(hash);

		repository.save(user);

		log.info("new user has been created: {}", user.getUsername());

	}

	@Override
	public User find(String username) {
		return repository.findByUsername(username);
	}

}
