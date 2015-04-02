package com.client.local;


import javax.ejb.CreateException;
import javax.ejb.EJBLocalHome;

public interface HelloWorldEJBLocalHome extends EJBLocalHome {

	public HelloWorldEJBLocalObject create() throws CreateException;

}
