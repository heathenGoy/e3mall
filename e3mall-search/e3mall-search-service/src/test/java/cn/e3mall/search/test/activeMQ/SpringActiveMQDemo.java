package cn.e3mall.search.test.activeMQ;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/applicationContext-activeMQ.xml")
public class SpringActiveMQDemo {
	
	/*@Autowired
	private JmsTemplate template;
	
	@Autowired
	private Queue queue;
	
	@Autowired
	private MessageCreator messageCreator;
	
	@Test
	public void sendMessage() {
		
		template.send(queue, 
				session ->session.createTextMessage("it's activeMQMessage! ")
		);	
		template.send(queue, this::messagecreate);
		
	}
	
	public Message messagecreate(Session session) throws JMSException {
		return session.createTextMessage("lambda Test!!");
	}
	*/
}
