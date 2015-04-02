package com.session.local;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;


public class HelloWorldEJBLocalBean implements SessionBean {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ejbRemove() throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setSessionContext(SessionContext arg0) throws EJBException, RemoteException {
		// TODO Auto-generated method stub
		
	}

	public void ejbCreate() throws CreateException {

	}

	
	public String greetings() {
		return "Local EJB says Hello World!!";
	}
	
	
	public String sendMessage() {
		
		String textMessage="Local Bean Said Hello!!";
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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		
		return "Local EJB sends message to local queue!!";
	}
	
}
