package com.chinamcloud.vpc.mq;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinamcloud.vpc.business.VPCBusiness;
import com.chinamcloud.vpc.entity.VpcDO;
import com.chinamcloud.vpc.util.mapper.JsonMapper;

@Component
public class VpcMQServiceImpl implements VpcMQService {

	protected static JsonMapper binder = JsonMapper.nonEmptyMapper();

	@Autowired
	private VPCBusiness business;

	/**
	 * 将Message转换成UTF-8字符串.
	 */
	protected static String EncodeMessage(Message message) {
		try {
			return new String(message.getBody(), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new String(message.getBody());
	}

	@Override
	public void resultVpc(Message message) {

		// 对json字符串进行UTF-8转码
		String receiveString = EncodeMessage(message);

		VpcDO vpcDO = binder.fromJson(receiveString, VpcDO.class);

		System.out.println("vpcDO result================:" + vpcDO);
		
		//根据TaskId获得Task对象,并根据情况填充code 和message
		
		business.updateVpc(vpcDO);

	}

}
