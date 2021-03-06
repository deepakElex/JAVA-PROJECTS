/**
 * 
 */
package com.vik;

import java.util.ArrayList;
import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
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
public class JMSReceiver {
	public static ArrayList<String> receive() {
		ArrayList<String> messages = new ArrayList<String>();
		QueueConnectionFactory factory = null;
		QueueConnection connection = null;
		QueueSession session = null;
		Queue queue = null;
		QueueReceiver receiver = null;

		try {
			// jndiProps application Server Defaults
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

			receiver = session.createReceiver(queue);
			connection.start();

			// System.out.println("**Receiving message from the queue**");

			Message message = null;
			do {
				message = receiver.receive(1000);
				if (message instanceof TextMessage) {
					TextMessage text = (TextMessage) message;
					System.out.printf("%s \t<----------<\t Received from the %s \n", text.getText(), queue.getQueueName());
					messages.add(text.getText());
				} else if (message instanceof ObjectMessage) {
					ObjectMessage object = (ObjectMessage) message;
					System.out.printf("%s \t<----------<\t Received from the %s \n", object.getObject(), queue.getQueueName());
				}
			} while (message != null);

			receiver.close();
			session.close();
			connection.close();
			// System.out.println("**Finish reading from the Queue**");
			return messages;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			if (receiver != null)
				try {
					receiver.close();
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
