package com.serv;

import java.io.IOException;

import java.util.Hashtable;

import javax.ejb.CreateException;
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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Calling the EJB Method");

		String callType = request.getParameter("call");
		Object HomeObj = null;
		String sendMsg = null;

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put("INITIAL_CONTEXT_FACTORY", "com.evermind.server.ApplicationClientInitialContextFactory");
		env.put("PROVIDER_URL", "ormi://localhost:12401");

		try {

			InitialContext itx = new InitialContext();
			// this will not affect as this is a local client anyways, test
			// using java to see the diff
			// InitialContext itx = new InitialContext(env);

			if ("remote".equals(callType)) {
				HomeObj = itx.lookup("java:comp/env/ejb/remoteVikrant");
				HelloWorldEJBHome home = (HelloWorldEJBHome) PortableRemoteObject.narrow(HomeObj, HelloWorldEJBHome.class);
				HelloWorldEJBObject remote = home.create();
				sendMsg = remote.greetings();

			} else {
				HomeObj = itx.lookup("java:comp/env/ejb/localVikrant");
				HelloWorldEJBLocalHome home = (HelloWorldEJBLocalHome) HomeObj;
				HelloWorldEJBLocalObject remote = home.create();
				sendMsg = remote.greetings();

			}

			String ServerName = (String) itx.lookup("java:comp/env/ServerName");

			request.setAttribute("server", ServerName);
			request.setAttribute("ejbmsg", sendMsg);

		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/success.jsp").forward(request, response);
		System.out.println("Response Sent");
	}
}
