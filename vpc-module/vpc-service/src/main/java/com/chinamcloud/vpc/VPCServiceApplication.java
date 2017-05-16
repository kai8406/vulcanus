package com.chinamcloud.vpc;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.BindingBuilder.DestinationConfigurer;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringCloudApplication
@EnableRabbit
@EnableFeignClients
public class VpcServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VpcServiceApplication.class, args);
	}

	@Bean
	@LoadBalanced
	RestTemplate restTemplate() {
		return new RestTemplate();
	}

	@Bean
	public TopicExchange topic() {
		return new TopicExchange("cmop.topic");
	}

	@Bean
	public DestinationConfigurer binding(TopicExchange topic) {
		return BindingBuilder.bind(topic);
	}

}
