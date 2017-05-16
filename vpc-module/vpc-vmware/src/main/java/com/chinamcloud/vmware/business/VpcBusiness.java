package com.chinamcloud.vmware.business;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinamcloud.vmware.entity.VpcDTO;
import com.chinamcloud.vmware.util.JsonMapper;

@Component
public class VpcBusiness {

	/**
	 * {@value}
	 */
	private static final String RESULT_VPC_SAVE_ROUTINGKEY = "result.vpc.save";

	@Autowired
	private RabbitTemplate template;

	protected static JsonMapper binder = JsonMapper.nonEmptyMapper();

	public void saveVpc(VpcDTO vpcDO) {

		/**
		 * 1.判断平台,如果是指定的平台,继续后续业务动作,否则放弃执行
		 * 
		 * 2.将参数填充SDK接口并执行,获得执行结果.
		 * 
		 * 3.将结果异步更新Task对象(只有执行失败才更新,并把失败的code和message放入task对象中)
		 * 
		 * 4.根据执行结果返回的ID,查询VPC对象,并将相关参数持久化,如status、vpcId、requestId等
		 * 
		 * 5.然后将vrouterId和routertableId异步发送到MQ中
		 * 
		 * 6.将查询结果异步发送到MQ中
		 */

		// step.1

		if (!"aliyun".equalsIgnoreCase(vpcDO.getPlatformId())) {
			return;
		}

		System.out.println("接受的DO:" + vpcDO);

		// step.2

		String requestId = "requestId-1111111111111111";
		String vpcId = "vpcId-1111111111111111";
		String routeTableId = "routeTableId-1111111111111111";
		String vRouterId = "vRouterId-1111111111111111";

		// step.3

		String taskId = vpcDO.getTaskId();
		// TaskDTO taskDTO = taskclient.findTask(taskId);
		// taskDTO.setstatus = "执行中";
		// taskDTO.setstatus = "执行失败";
		// template.convertAndSend("cmop.topic", TASK_UPDATE_ROUTINGKEY, binder.toJson(taskDTO));

		// step.4
		String status = "active";

		vpcDO.setUuid(vpcId);
		// vpcDO.setStatus(status);
		// vpcDO.setRequestId(requestId);
		// 持久化

		// Step.5
		// template.convertAndSend("cmop.topic", ALIYUN_VROUTER_SAVE_ROUTINGKEY, binder.toJson(vrouterDO));
		// template.convertAndSend("cmop.topic", ALIYUN_VROUTER_SAVE_ROUTINGKEY, binder.toJson(routetableDO));

		System.out.println("发送到servic的对象:" + binder.toJson(vpcDO));
		// Step.6
		template.convertAndSend("cmop.topic", RESULT_VPC_SAVE_ROUTINGKEY, binder.toJson(vpcDO));

	}

}
