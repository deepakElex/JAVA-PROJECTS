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

import com.client.HelloWorldEJBHome;
import com.client.HelloWorldEJBRemote;

public class EJBServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Calling the EJB Method");

		Hashtable<String, String> env = new Hashtable<String, String>();
		env.put("INITIAL_CONTEXT_FACTORY",
				"com.evermind.server.ApplicationClientInitialContextFactory");
		env.put("PROVIDER_URL", "ormi://localhost:12401");

		try {
			/*InitialContext itx = new InitialContext(env);*/  
			InitialContext itx = new InitialContext();  
			Object HomeObj = itx.lookup("HELLOWORLDEJB");

			HelloWorldEJBHome home = (HelloWorldEJBHome) PortableRemoteObject
					.narrow(HomeObj, HelloWorldEJBHome.class);
			HelloWorldEJBRemote remote = home.create();

			request.setAttribute("ejbmsg", remote.greetings());
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (CreateException e) {
			e.printStackTrace();
		}

		request.getRequestDispatcher("/success.jsp").forward(request, response);
		System.out.println("Response Sent");
	}
}
