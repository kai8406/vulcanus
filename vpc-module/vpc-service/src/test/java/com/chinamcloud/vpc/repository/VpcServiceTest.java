package com.chinamcloud.vpc.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.chinamcloud.vpc.VpcServiceApplication;
import com.chinamcloud.vpc.entity.VpcDO;
import com.chinamcloud.vpc.repository.db.VpcRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = VpcServiceApplication.class)
public class VpcServiceTest {

	@Autowired
	private VpcRepository repository;

	@Test
	public void createVPC() {

		VpcDO entity = new VpcDO();
		entity.setDescription("description");
		entity.setPlatformId("aws");
		entity.setCidrBlock("172.13.10.0/24");
		entity.setRegionId("cn-beijing");
		entity.setVpcName("vpcName");

		System.err.println(repository.saveAndFlush(entity));

	}

}
