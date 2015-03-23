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
		QueueConnectionFactory factory=null;
		QueueConnection connection=null;
		QueueSession session=null;
		 Queue queue =null;
		QueueSender sender=null;
		try {
			//jndiProps are comming from the jndi.properties file
			Context context = new InitialContext();
			
			 factory = (QueueConnectionFactory)context.lookup("ConnectionFactory");
	         connection = factory.createQueueConnection("test","test@123");;
	         session =    connection.createQueueSession(false,QueueSession.AUTO_ACKNOWLEDGE);
	         queue = (Queue)context.lookup("test/DummmyQueue");
	         sender = session.createSender(queue);
	        
	        System.out.println("Sending message to queue");
	         
	        TextMessage message1 = session.createTextMessage();
	        message1.setText(textMessage);
            sender.send(message1);
            System.out.println("1. Sent TextMessage to the Queue");
             
             
/*            ObjectMessage objMsg = session.createObjectMessage();
            String employee = new String("Employee - Vikrant");
            objMsg.setObject(employee);                     
            sender.send(objMsg);
            System.out.println("2. Sent ObjectMessage to the Queue");*/
	     
            return 1;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} finally {
            if (sender!=null)
				try {
					sender.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            if (session!=null)
				try {
					session.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            if (connection!=null)
				try {
					connection.close();
				} catch (JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
	}

}
