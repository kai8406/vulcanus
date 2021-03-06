package com.chinamcloud.vmware;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;

@SpringCloudApplication
@EnableRabbit
public class VPCVMwareApplication {

	public static void main(String[] args) {
		SpringApplication.run(VPCVMwareApplication.class, args);
	}

}
