<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="doctor.*"%>
<%@ page import="user.*"%>
<%@ page import="book.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./CSS/attendUser.css">
<title>Prescription</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		if(session.getAttribute("did")==null)
		{
			response.sendRedirect("signInDoctor.html");
		}
	%>
	<%
	String bid= request.getParameter("bid");									///booking id
	String id= request.getParameter("id");
	String did= request.getParameter("did");
	user u = userDao.getUserByid(id);
	bookDao.updateBookDb(bid);
	%>
	<div id="div9">PRESCRIPTION</h2></div>



<div id="div8">
  <form action="signOut" method="post"><button type="submit" id="btn">Logout</button></form>
</div>
	<div id="div1">
	<table border="1" cellpadding="5">
		<tr>
			<td id="td1">Booking ID:</td>
			<td><%=bid %></td>
		</tr>
		<tr>
			<td id="td1">Patient ID:</td>
			<td><%=id %></td>
		</tr>
		<tr>
			<td id="td1">Name:</td>
			<td><%=u.getName() %></td>
		</tr>
		<tr>
			<td id="td1">Gender:</td>
			<td><%=u.getGender() %></td>
		</tr>
		<tr>
			<td id="td1">Age:</td>
			<td><%=u.getAge() %></td>
		</tr>
		<tr>
			<td id="td1">Phone:</td>
			<td><%=u.getPhone() %></td>
		</tr>
	</table>
	</div>
	<form action="addPrescription" method="get">
		<div id="div3">
	<input type="hidden" id="bid" name="bid" value="<%=bid%>" >
	<textarea id = "prescription" name = "prescription" rows = "3" cols = "80">Enter prescription here</textarea><br>
		</div>
		<div id="div4">
	<input type="submit" value="Submit" id="btn" class="inp">
		</div>
	</form>
</body>
</html>