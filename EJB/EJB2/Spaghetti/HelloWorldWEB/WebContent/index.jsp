<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hello World Web</title>
</head>
<body>
	<h1>Hello World Web</h1>
	Click <a href="EJBGreetingServlet?call=remote">here</a> to call Remote EJB..
	<br />
	&emsp;Click <a href="EJBMDBServlet?call=remote">here</a> to call Remote EJB which will Put Dummy Msg in Remote Queue..
	<br />
	Click <a href="EJBGreetingServlet?call=local">here</a> to call Local EJB..
	<br />
	&emsp;Click <a href="EJBMDBServlet?call=local">here</a> to call Local EJB which will Put Dummy Msg in Local Queue..
	<br />
</body>
</html>