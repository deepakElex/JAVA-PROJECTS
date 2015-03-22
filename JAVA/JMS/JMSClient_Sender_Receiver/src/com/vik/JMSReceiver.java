/**
 * 
 */
package com.vik;

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
	public static void main(String[] args) {

		try {
			// jndiProps are comming from the jndi.properties file
			Context context = new InitialContext();

			QueueConnectionFactory factory = (QueueConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
			QueueConnection connection = factory.createQueueConnection("test","test@123");
			
			QueueSession session = connection.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
			Context contextw = new InitialContext();
			Queue queue = (Queue) context.lookup("test/DummmyQueue");
			QueueReceiver receiver = session.createReceiver(queue);
			connection.start();
			
			Message message = null;
			do {
				message = receiver.receive(1000);
				if (message instanceof TextMessage) {
					TextMessage text = (TextMessage) message;
					System.out.println("Received Test Message is : "	+ text.getText());
				} else if (message instanceof ObjectMessage) {
					ObjectMessage object = (ObjectMessage) message;
					System.out.println("Received Object Message is : "	+ object.getObject());
				}
			} while (message != null);
			
			receiver.close();
			session.close();
			connection.close();
			System.out.println("Finish reading from the Queue");


			
			/*
			 * ObjectMessage objMsg = session.createObjectMessage();
			 * 
			 * Employee employee = new Employee(); employee.setId(2163);
			 * employee.setName("Kumar_"+Math.random());
			 * employee.setDesignation("CTO"); employee.setSalary(100000);
			 * objMsg.setObject(employee); // sender.send(objMsg);
			 * System.out.println("2. Sent ObjectMessage to the Queue");
			 */

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
