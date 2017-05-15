package com.chinamcloud.task.business;

import org.springframework.stereotype.Component;

import com.chinamcloud.task.entity.TaskDTO;
import com.chinamcloud.task.util.JsonMapper;

@Component
public class TaskBusiness {

	protected static JsonMapper binder = JsonMapper.nonEmptyMapper();

	public TaskDTO saveTask(String vpcCode) {

		TaskDTO dto = new TaskDTO();
		dto.setId(1L);
		dto.setCode("task-code");
		dto.setResourceCode(vpcCode);

		// 拓扑图关系持久化
		return dto;
	}

	public TaskDTO getTask(String code) {

		TaskDTO dto = new TaskDTO();
		dto.setId(1L);
		dto.setCode(code);
		dto.setResourceCode("resoucesCode");

		// 拓扑图关系持久化
		return dto;
	}

}
