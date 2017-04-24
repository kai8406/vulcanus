package com.chinamcloud.vmware.mq;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Component
public class VPCMQServiceImpl implements VPCMQService {

	/**
	 * <p>
	 * 将{@link Message}转换成UTF-8字符串.
	 * <p>
	 * 
	 * @param message
	 * @return
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
	public void createVPC(Message message) {

		// 对json字符串进行UTF-8转码
		String receiveString = EncodeMessage(message);

		System.err.println("*************************");
		System.err.println(receiveString);
		System.err.println("*************************");

	}

}
