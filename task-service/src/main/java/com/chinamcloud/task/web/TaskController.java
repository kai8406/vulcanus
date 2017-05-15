package com.chinamcloud.task.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.chinamcloud.task.business.TaskBusiness;
import com.chinamcloud.task.entity.TaskDTO;

@RestController
public class TaskController {

	@Autowired
	private TaskBusiness business;

	@RequestMapping(value = "/task/{id}", method = RequestMethod.GET)
	public TaskDTO getTask(@PathVariable(value = "id") String id) {
		return business.getTask(id);
	}

	@RequestMapping(value = "/task/", method = RequestMethod.POST)
	public TaskDTO saveTask(@RequestBody TaskDTO taskDTO) {
		return business.saveTask(taskDTO);
	}

	@RequestMapping(value = "/task/", method = RequestMethod.PUT)
	public TaskDTO updateTask(@RequestBody TaskDTO taskDTO) {
		return business.updateTask(taskDTO);
	}

}
