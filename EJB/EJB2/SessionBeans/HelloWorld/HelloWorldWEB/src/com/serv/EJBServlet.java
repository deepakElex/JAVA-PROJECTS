package com.serv;

import java.io.IOException;

import java.rmi.RemoteException;
import java.util.Hashtable;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.client.local.HelloWorldEJBLocalHome;
import com.client.local.HelloWorldEJBLocalObject;
import com.client.remote.HelloWorldEJBHome;
import com.client.remote.HelloWorldEJBObject;

public class EJBServlet extends HttpServlet {

	 String JAVA_COMP_ENV_SERVER_NAME = "java:comp/env/ServerName";
	 
	 String remoteLookup = "java:comp/env/ejb/remoteVikrant";
	 String localLookup = "java:comp/env/ejb/localVikrant";

	String errormsg = "";
	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Calling the EJB Method");

		errormsg = "";

		String callType = request.getParameter("call");

		if ("remote".equals(callType)) {
			remoteCall(request);
		} else {
			localCall(request);
		}

		callServerName(request);

		request.setAttribute("errormsg", errormsg);

		request.getRequestDispatcher("/success.jsp").forward(request, response);
		System.out.println("Response Sent");
	}

	private void localCall(HttpServletRequest request) {
		try {
			InitialContext itx = getInitialContext();
			Object HomeObj = itx.lookup(localLookup);
			HelloWorldEJBLocalHome home = (HelloWorldEJBLocalHome) HomeObj;
			HelloWorldEJBLocalObject remote = home.create();
			request.setAttribute("ejbmsg", remote.greetings());
		} catch (Exception e) {
			e.printStackTrace();
			errormsg += e.getMessage() + "\n";
		}
	}

	private void remoteCall(HttpServletRequest request) {
		try {
			InitialContext itx = getInitialContext();
			Object HomeObj = itx.lookup(remoteLookup);
			HelloWorldEJBHome home = (HelloWorldEJBHome) PortableRemoteObject.narrow(HomeObj, HelloWorldEJBHome.class);
			HelloWorldEJBObject remote = home.create();
			request.setAttribute("ejbmsg", remote.greetings());
		} catch (Exception e) {
			e.printStackTrace();
			errormsg += e.getMessage() + "\n";
		}
	}

	private void callServerName(HttpServletRequest request) {
		try {
			InitialContext itx = getInitialContext();
			String ServerName = (String) itx.lookup(JAVA_COMP_ENV_SERVER_NAME);
			request.setAttribute("server", ServerName);
		} catch (Exception e) {
			e.printStackTrace();
			errormsg += e.getMessage() + "\n";
		}
	}

	private InitialContext getInitialContext() throws NamingException {
		
		return new InitialContext();
		
/*		//for oc4j Remote client same app only for both remote and local lookup
		//this is equivalent as new InitialContext();
		Hashtable env = new Hashtable(); 
		env.put("INITIAL_CONTEXT_FACTORY","com.evermind.server.ApplicationClientInitialContextFactory");
		env.put("PROVIDER_URL","ormi://localhost:12401"); 
		return new InitialContext(env);*/
		
		
/*		//for oc4j Remote Client (same web app, other Web app or java Client)
		//make sure u include the client jar in new web Or put it on class path for java client
		//make sure u have oc4jclient.jar in ur war lib
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.evermind.server.rmi.RMIInitialContextFactory");
		//select ur url with the application name if its deployed in other application or to use in same app but remotely
		env.put(Context.PROVIDER_URL, "ormi://localhost:23791/HelloWorldEAR"); //default dosen exposes ur interfaces for remote lookup :/ see oc4j jndi
		env.put(Context.SECURITY_PRINCIPAL, "oc4jadmin");
		env.put(Context.SECURITY_CREDENTIALS, "oc4jadmin");
		//the following lookups are directly to the root of the url, not ur orien or web xml 
		remoteLookup = "ejb/IAMVIKRANTREMOTE";  
		localLookup = "ejb/IAMVIKRANTRELOCAL"; //it wont work it will only if its local client(same app only)
		return new InitialContext(env);*/

		
/*		//for jboss Java Remote Client
		//make sure u include the client jar in new web Or put it on class path for java client
		//make sure u have jbossclient.jar in ur war lib
		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
		env.put(Context.PROVIDER_URL, "remote://localhost:4447"); //default dosen exposes ur interfaces :/ see oc4j jndi
		env.put(Context.SECURITY_PRINCIPAL, "test");
		env.put(Context.SECURITY_CREDENTIALS, "test@123");
		env.put("jboss.naming.client.ejb.context", "true");   // Requered must if this is a client ( in Client usage only only)
		localLookup="HelloWorldEAR/HelloWorldEJB/HELLOWORLDLOCALEJB!com.client.local.HelloWorldEJBLocalHome"; // wont work
		remoteLookup="HelloWorldEAR/HelloWorldEJB/HELLOWORLDREMOTEEJB!com.client.remote.HelloWorldEJBHome";
		return new InitialContext(env);*/
		

	}

}
