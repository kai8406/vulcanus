package com.chinamcloud.vpc.business;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.chinamcloud.vpc.client.TaskClient;
import com.chinamcloud.vpc.domain.CreateVpcRequest;
import com.chinamcloud.vpc.domain.TaskDTO;
import com.chinamcloud.vpc.domain.VPCVO;
import com.chinamcloud.vpc.service.db.VPCService;
import com.chinamcloud.vpc.utils.mapper.BeanMapper;
import com.chinamcloud.vpc.utils.mapper.JsonMapper;

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

	public VPCVO saveVPC(CreateVpcRequest request) {

		/**
		 * 
		 * 1.数据库持久化,得到VPC Id
		 * 
		 * 2.根据Token获得UserId
		 * 
		 * 3.创建一个Task对象
		 * 
		 * 4.将userId,TaskId,VPC参数封装成一个对象并传递至MQ队列里
		 * 
		 * 5.开启一个MQ监听器,监听core ms的执行结果
		 */

		// Step.1
		VPCVO vo = BeanMapper.map(request, VPCVO.class);

		// Step.2
		// String userId = getUserId(request.getAccess_token());

		// Step.3
		TaskDTO taskDTO = new TaskDTO();
		taskDTO.setAction("cmop.vpc.create");
		taskDTO.setRequestData(vo.toString());
		taskDTO.setState("执行中");
		taskDTO.setResourceCode(vo.getId());

		// TaskDTO task = taskClient.saveTask(taskDTO);

		// Step.4
		// template.convertAndSend(topic.getName(), "cmop.vpc.create", binder.toJson(vo));

		System.out.println("=======================");
		System.out.println(vo);
		VPCVO vpcvo = service.saveAndFlush(vo);
		System.out.println(vpcvo);
		return vpcvo;
	}

	public VPCVO getVPC() {
		return service.find("1");
	}

}
