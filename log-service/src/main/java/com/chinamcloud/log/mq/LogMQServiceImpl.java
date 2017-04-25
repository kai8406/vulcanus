package com.chinamcloud.log.mq;

import java.io.UnsupportedEncodingException;

import org.springframework.amqp.core.Message;
import org.springframework.stereotype.Component;

@Component
public class LogMQServiceImpl implements LogMQService {

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
	public void saveLog(Message message) {

		// 对json字符串进行UTF-8转码
		String receiveString = EncodeMessage(message);

		System.err.println("**********日志打印***************");
		System.err.println(receiveString);
		System.err.println("*************************");
	}

}
