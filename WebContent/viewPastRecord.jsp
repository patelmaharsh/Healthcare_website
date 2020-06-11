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
<link rel="stylesheet" href="./CSS/viewPastRecord.css"> 
</head>
<body>
	<%
		response.setHeader("Cache-Control","no-cache,must-revalidate");
		if(session.getAttribute("id")==null)
		{
			response.sendRedirect("signInUser.html");
		}
	%>
				<div id="div9">PAST APPOINTMENTS</div>       
			<div id="div8">
  <form action="signOut" method="post"><button type="submit" id="btn">Logout</button></form>
</div>
			
			<div id="div1">
				





			<table border="1" cellpadding="5">
	<%	HttpSession sesn=request.getSession();
		String id=String.valueOf(sesn.getAttribute("id"));
		ArrayList<Pair<book,String> > list= bookDao.viewPastRecord(id);
		int number = list.size();
		String map[] =new String[]{"","8:00","9:00", "10:00","11:00" ,"14:00","15:00","16:00","17:00","18:00","19:00"};
	%>
	
<% 	if(number!=0){
		    %>
		    
            <tr>
                <th>Booking ID</th>
                <th>Date</th>
                <th>Doctor ID</th>
                <th>Doctor Name</th>
                <th>Time Slot</th>
                <th>Category</th>
                <th>More</th>
                <th>Rate Visit</th>
            </tr> 
		    <%
			         for(int i=0;i<number;i++) {
			        book B=list.get(i).getKey();
			        String doc_name=list.get(i).getValue();
			%>   	 
				<tr>
	            <td><%=B.getBookingid() %></td>
	            <td><%=B.getDate()%></td>
	            <td><%=B.getDid()%></td>
	            <td><%=doc_name%></td>
	            <td><%=map[B.getTime_slot()]%></td>
	            <td><%=B.getCategory()%></td>
				<form method="post" action="viewRecord.jsp">
				<input type="hidden" id="bookingid" name="bookingid" value="<%=B.getBookingid()%>" >
				<input type="hidden" id="id" name="id" value="<%=B.getId()%>" >
				<input type="hidden" id="did" name="did" value="<%=B.getDid()%>" >
	            <td> <button type="submit" style="width: 150px;" id="btn">View Details</button></td>
	            <td> <button type="submit" formaction="rateDoctor.jsp" style="width: 150px;" id="btn">Rate!</button></td>
	            </form>
	         	</tr>
			 <%  }  
		    }
		         else
		         {
		        	 out.println("No Appointments!!");
		         }
         	%>
		</table>  
		</div>       	
</body>
</html>