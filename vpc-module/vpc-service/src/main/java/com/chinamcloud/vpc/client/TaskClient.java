package com.chinamcloud.vpc.client;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.chinamcloud.vpc.domain.TaskDTO;

@FeignClient("task-service")
public interface TaskClient {

	@RequestMapping(value = "/task/{code}", method = RequestMethod.GET)
	TaskDTO getTask(@PathVariable(value = "code") String code);

	@RequestMapping(value = "/task/", method = RequestMethod.POST)
	TaskDTO saveTask(@RequestParam(value = "vpcCode") String vpcCode);
}
