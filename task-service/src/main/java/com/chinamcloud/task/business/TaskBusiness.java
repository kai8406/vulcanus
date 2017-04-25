package com.chinamcloud.task.business;

import org.springframework.stereotype.Component;

import com.chinamcloud.task.domain.TaskDTO;
import com.chinamcloud.task.utils.JsonMapper;

@Component
public class TaskBusiness {

	protected static JsonMapper binder = JsonMapper.nonEmptyMapper();

	public TaskDTO saveTask() {

		TaskDTO dto = new TaskDTO();
		dto.setId(1L);
		dto.setCode("task-code");

		// 拓扑图关系持久化
		return dto;
	}

	public TaskDTO getTask(String code) {

		TaskDTO dto = new TaskDTO();
		dto.setId(1L);
		dto.setCode(code);

		// 拓扑图关系持久化
		return dto;
	}

}
