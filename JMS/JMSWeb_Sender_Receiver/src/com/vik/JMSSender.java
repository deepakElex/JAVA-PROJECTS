/**
 * 
 */
package com.vik;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 * @author aa
 * 
 */
public class JMSSender {

	public static int send(String textMessage) {
		QueueConnectionFactory factory = null;
		QueueConnection connection = null;
		QueueSession session = null;
		Queue queue = null;
		QueueSender sender = null;
		try {
			// jndiProps are comming from the jndi.properties file
			Context context = new InitialContext();
			// hardcoded ConnectionFactory so moved in env ref
			// factory = (QueueConnectionFactory)
			// context.lookup("ConnectionFactory");
			factory = (QueueConnectionFactory) context.lookup("java:comp/env/MYCONNFACTORY");
			// connection = factory.createQueueConnection("test", "test@123");
			// connection = factory.createQueueConnection("oc4jadmin",
			// "oc4jadmin");

			String username = (String) context.lookup("java:comp/env/username");
			String password = (String) context.lookup("java:comp/env/password");
			connection = factory.createQueueConnection(username, password);

			session = connection.createQueueSession(false, QueueSession.AUTO_ACKNOWLEDGE);

			// hardcoded test/DummmyQueue so moved in env ref
			// queue = (Queue) context.lookup("test/DummmyQueue");
			queue = (Queue) context.lookup("java:comp/env/MYQUEUE");
			sender = session.createSender(queue);

			// System.out.println("**Sending message to queue**");

			TextMessage message1 = session.createTextMessage();
			message1.setText(textMessage);
			sender.send(message1);
			System.out.printf("%s \t>---------->\t Sent to the %s \n", textMessage, queue.getQueueName());

			/*
			 * ObjectMessage objMsg = session.createObjectMessage(); String
			 * employee = new String("Employee - Vikrant");
			 * objMsg.setObject(employee); sender.send(objMsg);
			 * System.out.println("2. Sent ObjectMessage to the Queue");
			 */
			// System.out.println("**Sending finished**");
			return 1;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
			if (sender != null)
				try {
					sender.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (session != null)
				try {
					session.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (connection != null)
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

	}

}
