package com.chinamcloud.task.mq;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

public interface TaskMQService {
	
	
	
	@RabbitListener(
			bindings = @QueueBinding(
					exchange = @Exchange(value = "cmop.topic", durable = "true",autoDelete = "false",type = ExchangeTypes.TOPIC), 
					value = @Queue(value = "cmop.task", durable = "false",autoDelete="true"), 
					key = "cmop.task.save"
					)
		)
	public void saveTask(Message message);
	
	@RabbitListener(
			bindings = @QueueBinding(
					exchange = @Exchange(value = "cmop.topic", durable = "true",autoDelete = "false",type = ExchangeTypes.TOPIC), 
					value = @Queue(value = "cmop.task", durable = "false",autoDelete="true"), 
					key = "cmop.task.update"
					)
			)
	public void updateTask(Message message);

}
