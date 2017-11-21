package cn.e3mall.search.service.listener;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener{

	@Override
	public void onMessage(Message message) {
		if(message instanceof TextMessage) {
			
			try {
				
				String text = ((TextMessage) message).getText();
				System.out.println(text);
				
				
			} catch (JMSException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
