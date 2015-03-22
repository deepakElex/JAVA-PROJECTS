package com.client;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface HelloWorldEJBHome extends EJBHome {

	public HelloWorldEJBRemote create() throws RemoteException, CreateException;

}
