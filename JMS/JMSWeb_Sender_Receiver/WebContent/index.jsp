<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.util.ArrayList" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<font color="${color}"> ${msg} 
	</font>

	<h2>Hello JMS APP</h2> 


	<form action="actionPerformed">
		<label for="textMessage">Enter your Test Message Here:</label> 
		<input tabindex="1" type="text" name="textMessage" value="${textMessage}"> 
		<button  tabindex="2" type="submit" name="action" value="SEND" accesskey="S" ><u>S</u>END</button>
		<br /> <br /> 
		<button  tabindex="3" type="submit" name="action" value="RECEIVE" accesskey="C" >RE<u>C</u>EIVE</button>
		<br />
		<%
		try{
			if (request.getAttribute("messages")!= null){	
		ArrayList<String> messages =(ArrayList<String>)(request.getAttribute("messages"));
		for (String message:messages)
		{
		out.print("<p>"+message+"<p>");
		}}
		}catch(Exception e){
			e.printStackTrace();
		}
		%>
		<%-- <c:forEach items="${messages}" var="message">
			<p>Received Message: ${message}</p>
		</c:forEach> --%>
	</form>
	
</body>
</html>