<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Rate the doctor</title>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./CSS/rateDoctor.css">


<script type="text/javascript" src="./Javascript/rateDoctor.js"></script>
</head>

<body>
<%		
		String did=request.getParameter("did");
%>    
<div class="cont">
  <div class="stars">
    <form action="rateDoctor" method="post">             
      <input class="star star-5" id="star-5" type="radio" name="star" onclick="rating();"/>
      <label class="star star-5" for="star-5"></label>
      <input class="star star-4" id="star-4" type="radio" name="star" onclick="rating();"/>
      <label class="star star-4" for="star-4"></label>
      <input class="star star-3" id="star-3" type="radio" name="star" onclick="rating();"/>
      <label class="star star-3" for="star-3"></label>
      <input class="star star-2" id="star-2" type="radio" name="star" onclick="rating();"/>
      <label class="star star-2" for="star-2"></label>
      <input class="star star-1" id="star-1" type="radio" name="star" onclick="rating();"/>
      <label class="star star-1" for="star-1"></label>
      <input type="hidden" id="val" name="val"></input>
      <input type="hidden" id="did" name="did" value="<%=did%>"></input>
      <button type="submit">Rate!!</button>         
    </form>              
  </div>
</div>
</body>
</html>
