package com.chinamcloud.task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chinamcloud.task.business.TaskBusiness;
import com.chinamcloud.task.domain.TaskDTO;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskBusiness business;

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public TaskDTO getTask(@PathVariable(value = "code") String code) {
		return business.getTask(code);
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public TaskDTO saveTask(@RequestParam(value = "vpcCode") String vpcCode) {
		return business.saveTask();
	}

}
