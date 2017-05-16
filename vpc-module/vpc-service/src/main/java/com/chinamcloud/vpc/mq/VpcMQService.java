package com.chinamcloud.vpc.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public interface VpcMQService {
	
	@RabbitListener(
			bindings = @QueueBinding(
					exchange = @Exchange(value = "cmop.topic", durable = "true",autoDelete = "false",type = ExchangeTypes.TOPIC), 
					value = @Queue(value = "result.vpc.save", durable = "false",autoDelete="true"), 
					key = "result.vpc.save"
					)
		)
	public void resultVpc(Message message);

}
