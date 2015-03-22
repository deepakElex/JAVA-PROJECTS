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

	public static void main(String[] args) {

		try {
			//jndiProps are comming from the jndi.properties file
			Context context = new InitialContext();
			
			QueueConnectionFactory factory = (QueueConnectionFactory)context.lookup("jms/RemoteConnectionFactory");
	        QueueConnection connection = factory.createQueueConnection("test","test@123");;
	        QueueSession session =    connection.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
	        Context contextw = new InitialContext();
	        Queue queue = (Queue)context.lookup("test/DummmyQueue");
	        QueueSender sender = session.createSender(queue);
	        
	        System.out.println("Sending message to queue");
	         
	        TextMessage message1 = session.createTextMessage();
	        message1.setText("Hello ...This is a sample message..sending from a Remote Client");
            sender.send(message1);
            System.out.println("1. Sent TextMessage to the Queue");
             
             
            ObjectMessage objMsg = session.createObjectMessage();
            String employee = new String("Employee - Vikrant");
            objMsg.setObject(employee);                     
            sender.send(objMsg);
            System.out.println("2. Sent ObjectMessage to the Queue");
	     
            sender.close();
            session.close();
            connection.close();
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

}
