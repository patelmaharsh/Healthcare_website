<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="doctor.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>    
<!DOCTYPE html>





<html>
<head>
<meta charset="ISO-8859-1">
<title>View Slot</title>
<link rel="stylesheet" href="./CSS/viewSlot.css">
</head>
<body>
	<%
		response.setHeader("Cache-Control","no-cache,no-store,must-revalidate");
		if(session.getAttribute("id")==null)
		{
			response.sendRedirect("signInUser.html");
		}
		%>
		<div id="div9">Available Slots</h2></div>
		 <div id="div2">
    	<table>
    <tr>
        <td><form action="content.jsp" method="post"><button type="submit">Home</button></form></td>
    	<td><form action="signOut" method="post"><button type="submit">Logout</button></form></td>
    </tr>	
    </table>	
    </div>
<%	String  id=request.getParameter("id");
	String did=request.getParameter("did");
	String category=request.getParameter("category");
	ArrayList<ArrayList<String> > list= doctorDao.getSlotByDate(id,did);
	String map[] =new String[]{"","8:00","9:00", "10:00","11:00" ,"14:00","15:00","16:00","17:00","18:00","19:00"};
%>
<div id="div4">
<form method="get" action="bookSlot">
<% for(int i=0;i<10;i++)
	{
%>
<br>
<%=list.get(i).get(0)%>
<br>
<div class="switch-field">
<% 		for(int j=1;j<11;j++)
		{
			String val=list.get(i).get(0)+""+j;
			if(list.get(i).get(j).equals("0")){
%>			
				
			<input id="radio-<%=val %>" type="radio" value="<%=val%>" name="slot"/>
			<label for="radio-<%=val%>"><%=map[j] %></label>
<%			}		
		}
%>
</div>
<%
	}
%>
<input type="hidden" id="id" name="id" value=<%=id%> >
<input type="hidden" id="did" name="did" value="<%=did%>">
<input type="hidden" id="category" name="category" value="<%=category%>">
</div>
<div id="div5">
<button type="submit">Submit</button>
<input type="button" value="Go back!" onclick="history.back()">
</div>
</form>

</body>
</html>