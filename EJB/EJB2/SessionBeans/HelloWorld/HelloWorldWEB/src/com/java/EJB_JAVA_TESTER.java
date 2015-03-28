package com.java;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.client.local.HelloWorldEJBLocalHome;
import com.client.local.HelloWorldEJBLocalObject;
import com.client.remote.HelloWorldEJBHome;
import com.client.remote.HelloWorldEJBObject;

public class EJB_JAVA_TESTER {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		System.out.println("Calling the EJB Method");
		
		Object HomeObj = null;
		String sendMsg = null;

		
		
				//for oc4j Remote Client (same web app, other Web app or java Client)
				//make sure u include the client jar in new web Or put it on class path for java client
				//make sure u have oc4jclient.jar in ur war lib
				Hashtable<String, String> env = new Hashtable<String, String>();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "com.evermind.server.rmi.RMIInitialContextFactory");
				//select ur url with the application name if its deployed in other application or to use in same app but remotely
				env.put(Context.PROVIDER_URL, "ormi://localhost:23791/HelloWorldEAR"); //default dosen exposes ur interfaces for remote lookup :/ see oc4j jndi
				env.put(Context.SECURITY_PRINCIPAL, "oc4jadmin");
				env.put(Context.SECURITY_CREDENTIALS, "oc4jadmin");
				//the following lookups are directly to the root of the url, not ur orien or web xml 
				String remoteLookup = "ejb/IAMVIKRANTREMOTE";  
				String localLookup = "ejb/IAMVIKRANTRELOCAL"; //it wont work it will only if its local client(same app only)

				
/*				//for jboss Java Remote Client
				//make sure u include the client jar in new web Or put it on class path for java client
				//make sure u have jbossclient.jar in ur war lib
				Hashtable<String, String> env = new Hashtable<String, String>();
				env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
				env.put(Context.PROVIDER_URL, "remote://localhost:4447"); //default dosen exposes ur interfaces :/ see oc4j jndi
				env.put(Context.SECURITY_PRINCIPAL, "test");
				env.put(Context.SECURITY_CREDENTIALS, "test@123");
				env.put("jboss.naming.client.ejb.context", "true");   // Requered must if this is a client ( in Client usage only only)
				String localLookup="HelloWorldEAR/HelloWorldEJB/HELLOWORLDLOCALEJB!com.client.local.HelloWorldEJBLocalHome"; // wont work
				String remoteLookup="HelloWorldEAR/HelloWorldEJB/HELLOWORLDREMOTEEJB!com.client.remote.HelloWorldEJBHome";*/

		
		try {
 
			InitialContext itx = new InitialContext(env);

			HomeObj = itx.lookup(remoteLookup);
			HelloWorldEJBHome home = (HelloWorldEJBHome) PortableRemoteObject.narrow(HomeObj, HelloWorldEJBHome.class);
			HelloWorldEJBObject remote = home.create();
			sendMsg = remote.greetings();
			
			System.out.println(sendMsg);
			
			HomeObj = itx.lookup(localLookup);
			HelloWorldEJBLocalHome home2 = (HelloWorldEJBLocalHome) HomeObj;
			HelloWorldEJBLocalObject remote2 = home2.create();
			sendMsg = remote2.greetings();

			System.out.println(sendMsg);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		} catch (RemoteException e) {
			e.printStackTrace();
		}

		System.out.println("finish");

	}

}
