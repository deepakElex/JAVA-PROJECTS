
	<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html dir="RTL" lang="ar">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Success</title>


<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script type="text/javascript">
	function crunchifyAjax() {
		$.ajax({
			url : 'ajaxtest',
			success : function(data) {
				$('#result').html(data);
			}
		});
	}

	function alertAll() {
		alert('hello');

	}
	
	
	$( "#change" ).click(function() {
		  alert( "Changing the flow" );
		});
	
	
	
</script>


<script type="text/javascript">
	var intervalId = 0;
	//intervalId = setInterval(crunchifyAjax, 3000);
</script>



</head>
<body>
<a href='test'>a</a>


<c:url value="/getItemDetails" var="itemDetailsURL">
   <c:param name="itemId" value="I1980X"/>
</c:url>



<a href="/<c:out value="${itemDetailsURL}"/>">Click to get item details</a>

<c:url  value="/jsp/index.htm"/>


	<a href="/<c:url value="/test.jsp"/>"> This is a URL formed by
		&lt;c:url&gt; tag. </a>

	<h1>success ${Name}</h1>

	Current Locale : ${pageContext.response.locale}

	<form action="submitfrm">
		<input type="text" name="in" value="${val}" /> <input type="submit"
			value="Submit">
	</form>
	<div id="result"></div>
	<p>You have entered- ${val} </p>
	<input type="button" id="change"
			value="switch">
			
	<table border="1" style="width:300px">
<thead>
<tr>
<th>Name</th>
<th>Surname</th>
<th>Telephone</th>
</tr>
</thead>
<tbody>
<tr>
<td>Jack</td>
<td>Sales</td>
<td>555-5555</td>
</tr>
<tr>
<td>John</td>
<td>Admin</td>
<td>555-5555</td>
</tr>
<tr>
<td>James</td>
<td>Sales</td>
<td>555-5555</td>
</tr>
<tbody>
<tfoot>
<tr>
<td>Total</td>
<td>Total</td>
<td>Total</td>
</tr>
</tfoot>
</table>

<a href="<c:url value="jsonmethod"/>">Json you are great1 </a>
<br/>
<a href='jsonmethod'>Json you are great2 </a>
		
</body>

<script>
$( "p" ).click(function() {
  $( this ).slideUp();
});


$( "#change" ).click(function() {
	  
	
	if ($( "html" ).prop( "dir").toUpperCase() =="RTL".toUpperCase())
	
	$( "html" ).prop( "dir", "LTR" );
	else
		$( "html" ).prop( "dir", "RTL" );	
/* 	$( "input" ).prop( "checked", true );

	dir="rtl"

	$( "input" ).prop( "checked", true );

 */	
	});

</script>

</html>