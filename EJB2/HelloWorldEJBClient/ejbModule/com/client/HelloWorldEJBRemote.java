package com.client;

import java.rmi.RemoteException;

import javax.ejb.EJBObject;

public interface HelloWorldEJBRemote extends EJBObject {

	public String greetings() throws RemoteException;

}
