<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="book.*"%>
<%@ page import="book.book"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="javafx.util.Pair" %>         
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Past Records</title>
<link rel="stylesheet" href="./CSS/setLocation.css">

<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<script type="text/javascript" src="./Javascript/setLocation.js"></script>
</head>
<body>
  <%
    response.setHeader("Cache-Control","no-cache,must-revalidate");
    if(session.getAttribute("did")==null)
    {
      response.sendRedirect("signInDoctor.html");
    }
    int did = (int)session.getAttribute("did");
  %>
     <div id="div9">SET LOCATION</h2></div>



<div id="div8">
  <form action="signOut" method="post"><button type="submit" id="btn">Logout</button></form>
</div>


  <style>
  #mapCanvas {
    width: 500px;
    height: 400px;
    float: center;
    position: absolute;
    
        height: 400px; 
       	margin: 150px 200px 0px 500px; 
      
  }
  #infoPanel {
    float: left;
    margin-left: 10px;
  }
  #infoPanel div {
    margin-bottom: 5px;
  }
  </style>
  <div id="mapCanvas"></div>

      <div id="div1">
  <form method="post" action="setLocation">
  <input type="hidden" id="did" name="did" value="<%=did%>"></input>
  <input type="hidden" id="x" name="x"></input>
  <input type="hidden" id="y" name="y"></input>
  <button type="submit" style="width: 200px; margin-left: -70px;" id="btn">Set Location</button>
</form>
</div>
</body>
</html>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCWGixjkgHL7URrtWkfZ40ayJh8WjiTDpY&callback=initMap"
    async defer></script>
<script type="text/javascript">