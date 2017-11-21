package cn.e3mall.search.test.activeMQ;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.junit.Test;

public class ActiveMQDemo {
	
	@Test
	public void mqTopicProducer() throws JMSException {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.139.128:61616");
		Connection conn = factory.createConnection();
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("mytopic");
		MessageProducer producer = session.createProducer(topic);
		
		TextMessage message = new ActiveMQTextMessage();
		message.setText("hi! xxxxs");
		
		producer.send(message);
		
		producer.close();
		session.close();
		conn.close();
		
		
	}
	
	@Test
	public void mqTopicConsumer() throws JMSException, IOException {
		ConnectionFactory factory = new ActiveMQConnectionFactory("tcp://192.168.139.128:61616");
		Connection conn = factory.createConnection();
		conn.start();
		
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Topic topic = session.createTopic("mytopic");
		MessageConsumer consumer = session.createConsumer(topic);
		
		consumer.setMessageListener(new MessageListener() {
			
			@Override
			public void onMessage(Message message) {
				if(message instanceof TextMessage) {
					try {
						String text = ((TextMessage) message).getText();
						System.out.println(text);
						
						
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		
		System.in.read();
		
	}
	
	@Test
	public void mqConsumerSynchronized() throws JMSException {
		ConnectionFactory connFactory = new ActiveMQConnectionFactory("tcp://192.168.139.128:61616");
		Connection conn = connFactory.createConnection();
		
		//只有消费者需要保持连接的持续通畅, 所以Producer不需要conn.start(), 而Consumer 则需要
		conn.start();

		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
		// 如果没有那么就会被创建, 如果有这个队列, 那么会连接这个队列
		Queue queue = session.createQueue("myqueue");
		MessageConsumer consumer = session.createConsumer(queue);

		// 让客户一致等待接受消息
		/*
		 * while(true){ Message message = consumer.receive(20000); TextMessage tm =
		 * (TextMessage) message; //打印消息内容 System.out.println(tm.getText()); }
		 */

		consumer.setMessageListener(new MessageListener() {
			@Override
			public void onMessage(Message message) {
				if (message instanceof TextMessage) {
					TextMessage tm = (TextMessage) message;
					try {
						// 打印消息
						System.out.println(tm.getText());
					} catch (JMSException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
	}

	@Test
	public void teacherDemo() throws JMSException {
		// 1） 创建消息工厂， ActiveMQConnectionFactory，需要传递参数：
		ConnectionFactory connectionFactory = new
		ActiveMQConnectionFactory(
		"tcp://192.168.139.128:61616");
		// 2） 从工厂中获取连接
		Connection connection =
		connectionFactory.createConnection();
		// 3） 开启连接
		connection.start();
		// 4） 从连接中获取Session
		//第一个参数：消息事务，如果第一个参数是true，第二个参数将会被
		//第二个参数：消息应答模式，自动应答模式
		Session session = connection.createSession(false,
		Session.AUTO_ACKNOWLEDGE);
		// 5） 从Session中获取消息目的地
		//创建一个消息目的地，给消息目的起一个名称： myqueue
		//相当域在ActiveMQ服务中开辟了名称为myqueue一块空间，消息发送
		//消息有两种模式：点对点（queue），发布定阅模式：（Topic）
		Queue queue = session.createQueue("myqueue");
		//6）创建消息接受者
		MessageConsumer consumer = session.createConsumer(queue);
		//让客户一致等待接受消息
		while(true){
		Message message = consumer.receive(20000);
		TextMessage tm = (TextMessage) message;
		//打印消息内容
		System.out.println(tm.getText());
		}
		

	}

	@Test
	public void mqDemo() throws JMSException {

		ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://192.168.139.128:61616");

		Connection conn = connectionFactory.createConnection();
		// 第一个参数:是否支持事务，如果为true，则会忽略第二个参数，被jms服务器设置为SESSION_TRANSACTED
		// 第二个参数为false时，paramB的值可为Session.AUTO_ACKNOWLEDGE，Session.CLIENT_ACKNOWLEDGE，DUPS_OK_ACKNOWLEDGE其中一个。
		// Session.AUTO_ACKNOWLEDGE为自动确认，客户端发送和接收消息不需要做额外的工作。哪怕是接收端发生异常，也会被当作正常发送成功。
		// Session.CLIENT_ACKNOWLEDGE为客户端确认。客户端接收到消息后，必须调用javax.jms.Message的acknowledge方法。jms服务器才会当作发送成功，并删除消息。
		// DUPS_OK_ACKNOWLEDGE允许副本的确认模式。一旦接收方应用程序的方法调用从处理消息处返回，会话对象就会确认消息的接收；而且允许重复确认。
		Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);

		// 创建queue
		Queue queue = session.createQueue("myqueue");
		// 创建某个queue 的生产者
		MessageProducer producer = session.createProducer(queue);

		TextMessage message = new ActiveMQTextMessage();
		message.setText("hello world!");

		producer.send(message);

		producer.close();

		session.close();
		conn.close();
	}
}
