package com.message;
import javax.ejb.*;
import javax.jms.*;

public class HelloWorldEJBMessageDrivenBean implements MessageDrivenBean, MessageListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void onMessage(Message message) {
		if (message instanceof TextMessage) {
			TextMessage text = (TextMessage) message;
			try {
				System.out.println("HelloWorldEJBMessageDrivenBean Received Test Message is : "	+ text.getText());
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (message instanceof ObjectMessage) {
			ObjectMessage object = (ObjectMessage) message;
			try {
				System.out.println("Received Object Message is : "	+ object.getObject());
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
