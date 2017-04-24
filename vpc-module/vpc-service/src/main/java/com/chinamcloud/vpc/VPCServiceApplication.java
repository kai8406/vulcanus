package com.chinamcloud.vpc;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.core.BindingBuilder.DestinationConfigurer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.context.annotation.Bean;

@SpringCloudApplication
public class VPCServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(VPCServiceApplication.class, args);
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
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
