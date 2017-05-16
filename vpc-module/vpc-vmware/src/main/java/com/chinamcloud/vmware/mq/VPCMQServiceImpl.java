package com.chinamcloud.vmware.mq;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.chinamcloud.vmware.business.VpcBusiness;
import com.chinamcloud.vmware.entity.VpcDTO;
import com.chinamcloud.vmware.util.JsonMapper;

@Component
public class VpcMQServiceImpl implements VpcMQService {

	@Autowired
	private VpcBusiness business;

	protected static JsonMapper binder = JsonMapper.nonDefaultMapper();

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
	public void saveVpc(Message message) {

		// 对json字符串进行UTF-8转码
		String receiveString = EncodeMessage(message);

		System.out.println("receiveString" + receiveString);

		VpcDTO vpcDO = binder.fromJson(receiveString, VpcDTO.class);

		System.err.println("*************************");
		System.err.println(vpcDO);
		System.err.println("*************************");

		 business.saveVpc(vpcDO);

	}

}
