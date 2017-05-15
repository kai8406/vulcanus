package com.chinamcloud.task.mq;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinamcloud.task.business.TaskBusiness;
import com.chinamcloud.task.entity.TaskDTO;
import com.chinamcloud.task.util.JsonMapper;

@Component
public class TaskMQServiceImpl implements TaskMQService {

	@Autowired
	private TaskBusiness business;

	protected static JsonMapper binder = JsonMapper.nonEmptyMapper();

	/**
	 * 对json字符串进行UTF-8转码,将Message转换成UTF-8字符串.
	 */
	protected static String EncodeMessage(Message message) {
		try {
			return new String(message.getBody(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new String(message.getBody());
	}

	@Override
	public void saveTask(Message message) {

		String receiveString = EncodeMessage(message);

		TaskDTO taskDTO = binder.fromJson(receiveString, TaskDTO.class);
		
		System.err.println(business.saveTask(taskDTO));

	}

	@Override
	public void updateTask(Message message) {

		String receiveString = EncodeMessage(message);

		TaskDTO taskDTO = binder.fromJson(receiveString, TaskDTO.class);

		System.err.println(business.updateTask(taskDTO));
	}

}
