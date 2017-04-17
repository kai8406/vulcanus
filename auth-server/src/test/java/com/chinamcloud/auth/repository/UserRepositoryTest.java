package com.chinamcloud.auth.repository;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chinamcloud.auth.AuthServerApplication;
import com.chinamcloud.auth.domain.User;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AuthServerApplication.class)
public class UserRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Test
	public void saveAndFindUserByName() {

		User user = new User();
		user.setUsername("name");
		user.setPassword("password");
		repository.save(user);

		User found = repository.findByUsername(user.getUsername());

		assertEquals(user.getUsername(), found.getUsername());
		assertEquals(user.getPassword(), found.getPassword());
	}

}
