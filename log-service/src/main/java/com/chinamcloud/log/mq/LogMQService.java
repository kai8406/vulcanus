package com.chinamcloud.log.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public interface LogMQService {
	
	@RabbitListener(
			bindings = @QueueBinding(
					exchange = @Exchange(value = "cmop.topic", durable = "true",autoDelete = "false",type = ExchangeTypes.TOPIC), 
					value = @Queue(value = "cmop.log", durable = "false",autoDelete="true"), 
					key = "cmop.*.*"
					)
		)
	public void saveLog(Message message);

}
