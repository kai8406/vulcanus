package com.chinamcloud.task.business;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinamcloud.task.entity.TaskDTO;
import com.chinamcloud.task.service.db.TaskService;
import com.chinamcloud.task.util.JsonMapper;

@Component
public class TaskBusiness {

	@Autowired
	private TaskService service;

	protected static JsonMapper binder = JsonMapper.nonEmptyMapper();

	public TaskDTO saveTask(TaskDTO taskDTO) {

		taskDTO.setCreateTime(new Date());

		return service.saveAndFlush(taskDTO);
	}

	public TaskDTO getTask(String id) {
		return service.find(id);
	}

	public TaskDTO updateTask(TaskDTO taskDTO) {

		taskDTO.setModifyTime(new Date());

		return service.saveAndFlush(taskDTO);
	}

}
