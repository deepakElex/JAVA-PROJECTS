package com.message;

import javax.ejb.*;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class HelloWorldEJBMessageDrivenBean implements MessageDrivenBean, MessageListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void onMessage(Message message) {

		String MDB_TYPE = "";

		try {
			Context context = new InitialContext();
			MDB_TYPE = (String) context.lookup("java:comp/env/MDB_TYPE");

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (message instanceof TextMessage) {
			TextMessage text = (TextMessage) message;
			try {
				System.out.println("Via " + MDB_TYPE + ", MDB Received Message : " + text.getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (message instanceof ObjectMessage) {
			ObjectMessage object = (ObjectMessage) message;
			try {
				System.out.println("Received Object Message is : " + object.getObject());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	@Override
	public void ejbRemove() throws EJBException {
		// TODO Auto-generated method stub

	}

	@Override
	public void setMessageDrivenContext(MessageDrivenContext arg0) throws EJBException {
		// TODO Auto-generated method stub

	}

	public void ejbCreate() {
		// TODO Auto-generated method stub

	}

}
