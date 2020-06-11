<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./CSS/content_doc.css">



<title>Home-medicart</title>
</head>
<body>   

<%
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); 
	if(session.getAttribute("did")==null)
	{
		System.out.println(session.getAttribute("did"));
		response.sendRedirect("signInDoctor.html");
	}
%>	
	<header><h1>Appointment</h1></header>         
<div id="div2">
  <nav>
  	<form action="content_doc.jsp" method="post"><button type="submit">Home</button></form>
    <form action="signOut" method="post"><button type="submit">Logout</button></form>


  </nav>
  </div>
  
  <div id="div3">
	<form action="doctorViewBooking.jsp" method="post">
		<div class="container">
			<button type="submit" class="searchbtn">View Pending Appointments</button>
		</div>
	</form> 
	 </div>	
	 <div id="div3">
	<form action="setLocation.jsp" method="post">
		<div class="container">
			<button type="submit" class="searchbtn">Set Location</button>
		</div>
	</form> 
	 </div>	
</body>
</html>