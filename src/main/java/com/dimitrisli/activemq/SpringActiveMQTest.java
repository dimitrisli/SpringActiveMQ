package com.dimitrisli.activemq;

import javax.jms.JMSException;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQDestination;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;

public class SpringActiveMQTest {

	public static void main(String[] args) throws JMSException {

		ApplicationContext context = new ClassPathXmlApplicationContext("spring/jms/jms-context.xml");

		JmsTemplate template = (JmsTemplate) context.getBean("jmsTemplate");
		ActiveMQDestination destination = (ActiveMQDestination) context.getBean("destination");

		// sending a message
		template.convertAndSend(destination, "Hi");

		// receiving a message
		Object msg = template.receive(destination);
		if (msg instanceof TextMessage) {
			try {
				System.out.println(((TextMessage) msg).getText());
			} catch (JMSException e) {
				System.out.println(e);
			}
		}

	}
}