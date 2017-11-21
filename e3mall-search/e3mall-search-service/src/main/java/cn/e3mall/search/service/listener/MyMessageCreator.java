package cn.e3mall.search.service.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.MessageCreator;

public class MyMessageCreator implements MessageCreator{

	@Override
	public Message createMessage(Session session) throws JMSException {
		
		return session.createTextMessage("it's my message!!!!");
	}

}
