package com.chinamcloud.vpc.business;

import java.util.Date;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.chinamcloud.vpc.client.TaskClient;
import com.chinamcloud.vpc.client.TaskDTO;
import com.chinamcloud.vpc.client.TaskStatusEnum;
import com.chinamcloud.vpc.entity.CreateVpcRequest;
import com.chinamcloud.vpc.entity.DeleteVpcRequest;
import com.chinamcloud.vpc.entity.UpdateVpcRequest;
import com.chinamcloud.vpc.entity.VpcDO;
import com.chinamcloud.vpc.service.db.VpcService;
import com.chinamcloud.vpc.util.exception.RestResult;
import com.chinamcloud.vpc.util.mapper.BeanMapper;
import com.chinamcloud.vpc.util.mapper.JsonMapper;

@Component
public class VPCBusiness {

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private TopicExchange topic;

	@Autowired
	private VpcService service;

	@Autowired
	private TaskClient taskClient;

	@Autowired
	private RestTemplate restTemplate;

	protected static JsonMapper binder = JsonMapper.nonEmptyMapper();

	private String getUserId(String accessToken) {
		return restTemplate.getForEntity("http://AUTH-SERVER/users/id?access_token=" + accessToken, String.class)
				.getBody();
	}

	/**
	 * {@value}
	 */
	private static final String VPC_SAVE_ROUTINGKEY = "cmop.vpc.save";

	public RestResult<VpcDO> saveVpc(CreateVpcRequest request) {

		/**
		 * 1.根据Token获得UserId
		 * 
		 * 2.持久化VpcDO
		 * 
		 * 3.调用HTTP API创建TaskDTO
		 * 
		 * 4.将TaskId封装至VpcDO,注意:不要对taskID做持久化操作.
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
		taskDTO.setAction(VPC_SAVE_ROUTINGKEY);
		taskDTO.setRequestData(requestDO.toString());
		taskDTO.setResourceId(requestDO.getId());

		TaskDTO saveTaskDTO = taskClient.saveTask(taskDTO);

		// Step.4

		requestDO.setTaskId(saveTaskDTO.getId());

		// Step.5
		template.convertAndSend(topic.getName(), VPC_SAVE_ROUTINGKEY, binder.toJson(requestDO));

		RestResult<VpcDO> result = new RestResult<>();
		result.setSuccessResult(requestDO);

		return result;
	}

	public VpcDO updateVpc(VpcDO vpcDO) {

		// 修改Task 状态信息,根据task status 判断.

		return service.saveAndFlush(vpcDO);
	}

	public RestResult<VpcDO> updateVpc(UpdateVpcRequest vpc) {

		VpcDO vpcDO = service.find(vpc.getVpcId());

		vpcDO.setDescription(vpc.getDescription());
		vpcDO.setVpcName(vpc.getVpcName());

		vpcDO = service.saveAndFlush(vpcDO);

		RestResult<VpcDO> result = new RestResult<>();
		result.setSuccessResult(vpcDO);

		return result;
	}

	public RestResult<?> removeVpc(DeleteVpcRequest vpc) {

		VpcDO vpcDO = service.find(vpc.getVpcId());
		vpcDO.setActive("N");
		vpcDO = service.saveAndFlush(vpcDO);

		return new RestResult<>();
	}

	public RestResult<VpcDO> getVpc(String id) {
		VpcDO vpcDO = service.find(id);

		RestResult<VpcDO> result = new RestResult<>();
		result.setSuccessResult(vpcDO);

		return result;
	}

}
