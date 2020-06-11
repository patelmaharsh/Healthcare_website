<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./CSS/content.css">


<title>book appointment</title>
</head>
<body>   

<%
	response.setHeader("Cache-Control","no-cache,no-store,must-revalidate"); 
	if(session.getAttribute("id")==null)
	{
		System.out.println(session.getAttribute("id"));
		response.sendRedirect("signInUser.html");
	}
%>	
	<div id="div1">BOOK APPOINTMENT</div>       

<div id="div3">
  <form action="signOut" method="post"><button type="submit" id="btn">Logout</button></form>
</div>




<div id="div2">
<form action="viewList.jsp" method="post">
			
			<label for="city"><b>City</b></label><br> 
			<input type="text" placeholder="Enter City" name="city" required style="width: 400px;"><br>
			<label for="category"><b>Category</b></label><br> 
			<select name="category" style="width: 430px;">
			    <option value="Gastroenterologist">Gastroenterologist</option>
			    <option value="General Physician">General Physician</option>
			    <option value="Gynaecologist">Gynaecologist</option>
			    <option value="Paediatrician">Paediatrician</option>
			    <option value="Psychiatrist">Psychiatrist</option>
			    <option value="Dietician">Dietician</option>
			    <option value="Orthopedician">Orthopedician</option>
			    <option value="Diabetologist">Diabetologist</option>
			    <option value="Oncologist">Oncologist</option>
			    <option value="Cardiologist">Cardiologist</option>
			 </select><br>  
			<div style="padding-left: 120px;">
			 <button type="reset" class="cancelbtn" style="width: 100px;" id="btn">Cancel</button>
			 <button type="submit" class="searchbutt" style="width: 100px;" id="btn">Search</button></div>
	</form>
	
 </div>
</body>
</html>