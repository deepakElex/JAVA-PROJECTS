package com.message;

import javax.ejb.*;
import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.http.HttpServletRequest;

import com.client.local.HelloWorldEJBLocalHome;
import com.client.local.HelloWorldEJBLocalObject;
import com.client.remote.HelloWorldEJBHome;
import com.client.remote.HelloWorldEJBObject;

public class HelloWorldEJBMessageDrivenBean implements MessageDrivenBean, MessageListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void onMessage(Message message) {

		String MDB_NAME = "";

		try {
			Context context = new InitialContext();
			MDB_NAME = (String) context.lookup("java:comp/env/MDB_NAME");

		} catch (Exception e) {
			e.printStackTrace();
		}

		if (message instanceof TextMessage) {
			TextMessage text = (TextMessage) message;
			try {
				System.out.println(EjbGreeting());
				System.out.println("Via " + MDB_NAME + ", MDB Received Message : " + text.getText());
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

	private String EjbGreeting() {

		String MDB_TYPE = "";
		String greeting ="";

		try {
			Context context = new InitialContext();
			MDB_TYPE = (String) context.lookup("java:comp/env/MDB_TYPE");

			if ("LOCAL".equals(MDB_TYPE))
				greeting= localCall();
			else
				greeting= remoteCall();
		} catch (Exception e) {

			e.printStackTrace();
		}
		return greeting;
	}

	private String localCall() {
		String greeting = "NULL";
		try {

			String localLookup = "java:comp/env/ejb/localVikrant";
			InitialContext itx = new InitialContext();
			Object HomeObj = itx.lookup(localLookup);
			HelloWorldEJBLocalHome home = (HelloWorldEJBLocalHome) HomeObj;
			HelloWorldEJBLocalObject local = home.create();
			greeting = local.greetings();
		} catch (Exception e) {
			e.printStackTrace();
			// errormsg += e.getMessage() + "\n";
		}
		return greeting;
	}

	private String remoteCall() {
		String greeting = "NULL";
		try {
			String remoteLookup = "java:comp/env/ejb/remoteVikrant";
			InitialContext itx = new InitialContext();
			Object HomeObj = itx.lookup(remoteLookup);
			HelloWorldEJBHome home = (HelloWorldEJBHome) PortableRemoteObject.narrow(HomeObj, HelloWorldEJBHome.class);
			HelloWorldEJBObject remote = home.create();
			greeting = remote.greetings();
		} catch (Exception e) {
			e.printStackTrace();
			// errormsg += e.getMessage() + "\n";
		}
		return greeting;
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
