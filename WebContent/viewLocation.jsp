<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="doctor.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="javafx.util.Pair" %>   
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=false"></script>
<meta charset="ISO-8859-1">
<meta name="viewport" content="initial-scale=1.0, user-scalable=no">
<title>View Doctor Location</title>
<link rel="stylesheet" href="./CSS/viweLocaton.css">
</head>
<body>
	<%
		String did ="18";//request.getParameter("did");
		ArrayList<String> p = doctorDao.viewLocation(did);
		String x = p.get(0);
		String y= p.get(1);
		String area = p.get(2);
		String city = p.get(3);
		String state = p.get(4);
	%>
	<div id="div1">
	<header><h1>Doctor's Location</h1></header>
	</div>
	<style>
      /* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
     #map { 
        height: 400px; 
       	margin: 50px 200px 0px 200px; 
       } 
      /* Optional: Makes the sample page fill the window. */
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
    </style>
  </head>
  <body>
  
  	<input type="hidden" id="x" value="<%=x%>"></input> 
  	<input type="hidden" id="y" value="<%=y%>"></input>
    <div id="map"></div> 
    <div id="div2">
    <h2>Area  :<%=area %></h2>
    <h2>City  :<%=city %></h2>
    <h2>State :<%=state %></h2>
    </div>
    <script src="./Javascript/viewLocation.js"> 
      
    </script> 
    
</body>
</html>
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCWGixjkgHL7URrtWkfZ40ayJh8WjiTDpY&callback=initMap"
    async defer></script>
