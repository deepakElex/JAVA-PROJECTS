package com.client.remote;

import java.rmi.RemoteException;

import javax.ejb.CreateException;
import javax.ejb.EJBHome;

public interface HelloWorldEJBHome extends EJBHome {

	public HelloWorldEJBObject create() throws CreateException , RemoteException ;

}
