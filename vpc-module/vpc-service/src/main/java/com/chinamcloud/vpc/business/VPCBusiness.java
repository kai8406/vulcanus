package com.chinamcloud.vpc.business;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinamcloud.vpc.client.TaskClient;
import com.chinamcloud.vpc.domain.TaskDTO;
import com.chinamcloud.vpc.domain.VPCVO;
import com.chinamcloud.vpc.service.db.VPCService;
import com.chinamcloud.vpc.utils.JsonMapper;

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

	protected static JsonMapper binder = JsonMapper.nonEmptyMapper();

	public VPCVO saveVPC() {

		VPCVO vo = new VPCVO();
		vo.setCidr("172.16.0.0/16");
		vo.setDescription("默认VPC");
		vo.setPlatformId("aws");
		vo.setRegionId("beijing-2");
		vo.setUuid("461D0C42-D5D1-4009-9B6A-B3D5888A19A9");
		vo.setVpcName("默认VPC");
		vo.setVpcCode("vpc-code");

		TaskDTO taskDTO = taskClient.saveTask("vpc-code111");

		System.out.println("vpc business 中打印出的task code:" + taskDTO.getCode());

		template.convertAndSend(topic.getName(), "cmop.vpc.create", binder.toJson(vo));

		// 拓扑图关系持久化

		return service.saveAndFlush(vo);
	}

	public VPCVO getVPC() {
		return service.find(1);
	}

}
