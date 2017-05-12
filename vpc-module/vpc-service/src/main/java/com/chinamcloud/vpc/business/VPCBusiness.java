package com.chinamcloud.vpc.business;

import java.util.Date;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.chinamcloud.vpc.client.TaskClient;
import com.chinamcloud.vpc.entity.CreateVpcRequest;
import com.chinamcloud.vpc.entity.TaskDTO;
import com.chinamcloud.vpc.entity.VpcDO;
import com.chinamcloud.vpc.service.db.VPCService;
import com.chinamcloud.vpc.util.mapper.BeanMapper;
import com.chinamcloud.vpc.util.mapper.JsonMapper;

@Component
public class VPCBusiness {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private TopicExchange topic;

	@Autowired
	private VPCService service;

	@Autowired
	private TaskClient taskClient;

	@Autowired
	private RestTemplate restTemplate;

	protected static JsonMapper binder = JsonMapper.nonEmptyMapper();

	private String getUserId(String accessToken) {
		return restTemplate.getForEntity("http://AUTH-SERVER/users/id?access_token=" + accessToken, String.class)
				.getBody();
	}

	private static final String VPC_CREATE_ROUTINGKEY = "cmop.vpc.create";

	public VpcDO saveVPC(CreateVpcRequest request) {

		/**
		 * 1.根据Token获得UserId
		 * 
		 * 2.持久化VpcDO
		 * 
		 * 3.调用HTTP API创建TaskDTO
		 * 
		 * 4.将TaskId封装至VpcDO持久化
		 * 
		 * 5.将VpcDO传递至MQ队列里
		 * 
		 */

		// Step.1
		String userId = getUserId(request.getAccess_token());

		// Step.2
		VpcDO requestDO = BeanMapper.map(request, VpcDO.class);
		requestDO.setCreateTime(new Date());
		requestDO.setUserId(userId);

		requestDO = service.saveAndFlush(requestDO);

		// Step.3
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setStatus(TaskStatusEnum.执行中.toString());
		taskDTO.setAction(VPC_CREATE_ROUTINGKEY);
		taskDTO.setRequestData(requestDO.toString());
		taskDTO.setResourceId(requestDO.getId());

		TaskDTO saveTaskDTO = taskClient.saveTask(taskDTO);

		// Step.4

		requestDO.setTask_id(saveTaskDTO.getId());
		requestDO = service.saveAndFlush(requestDO);

		// Step.5
		template.convertAndSend(topic.getName(), VPC_CREATE_ROUTINGKEY, binder.toJson(requestDO));

		return requestDO;
	}

	public VpcDO getVPC() {
		return service.find("1");
	}

}
