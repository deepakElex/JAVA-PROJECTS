package com.client.remote;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBObject;

public interface HelloWorldEJBObject extends EJBObject {

	public String greetings() throws CreateException , RemoteException ;

}
