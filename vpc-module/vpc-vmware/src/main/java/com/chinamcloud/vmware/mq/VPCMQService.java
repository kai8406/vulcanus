package com.chinamcloud.vmware.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public interface VPCMQService {
	
	@RabbitListener(
			bindings = @QueueBinding(
					exchange = @Exchange(value = "cmop.topic", durable = "true",autoDelete = "false",type = ExchangeTypes.TOPIC), 
					value = @Queue(value = "cmop.vpc.vmware.create", durable = "false",autoDelete="true"), 
					key = "cmop.vpc.create"
					)
		)
	public void createVPC(Message message);

}
