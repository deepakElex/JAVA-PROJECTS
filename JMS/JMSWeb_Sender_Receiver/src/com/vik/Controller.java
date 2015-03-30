package com.vik;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class Controller extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("SEND".equals(request.getParameter("action"))) {
			
			String textMessage = (String) request.getParameter("textMessage");
			int cnt = JMSSender.send(textMessage);
			request.setAttribute("color", cnt == 0 ? "red" : "green");
			request.setAttribute("msg", cnt == 0 ? "** Problem Occured," + "" + " could not send message " : "** Message Sent Successfully");

			if (cnt == 0)  
				request.setAttribute("textMessage", textMessage);
			else 
				request.setAttribute("textMessage", "");
		}
		if ("RECEIVE".equals(request.getParameter("action"))) {
			ArrayList<String> msgs = JMSReceiver.receive();
			request.setAttribute("messages", msgs);

			if (msgs == null) {
				request.setAttribute("color", "red");
				request.setAttribute("msg", "** Error Occured While Receiving msgs..");
			} else {
				request.setAttribute("color", "green");
				if (msgs.size() > 0)
					request.setAttribute("msg", "** Message Received Successfully..");
				else
					request.setAttribute("msg", "** No More Messages to Read..");
			}
		}

		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}
}
