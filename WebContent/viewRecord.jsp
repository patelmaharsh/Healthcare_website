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
<title>Medical Records</title>
<link rel="stylesheet" href="./CSS/viewRecord.css">
</head>
<body>
	<%
		response.setHeader("Cache-Control","no-cache,must-revalidate");
		if(session.getAttribute("id")==null)
		{
			response.sendRedirect("signInUser.html");
		}
	%>
	<%	HttpSession sesn=request.getSession();
		String bookingid = request.getParameter("bookingid");
		Pair<book,String> record= bookDao.viewRecord(bookingid);
		String map[] =new String[]{"","8:00","9:00", "10:00","11:00" ,"14:00","15:00","16:00","17:00","18:00","19:00"};
		book B=record.getKey();
        String doc_name=record.getValue();
	%>
	
	<div id="div9">MEDICAL RECORD</h2></div>



<div id="div8">
  <form action="signOut" method="post"><button type="submit" id="btn">Logout</button></form>
</div>
    
                <div id="div1">
                <table border="1" cellpadding="5">
               	<tr>
                    <th>Booking ID</th>
                    <th>Date</th>
                    <th>Doctor ID</th>
                    <th>Doctor Name</th>
                    <th>Time Slot</th>
                    <th>Category</th> 
                </tr>
                <tr>
                    <td><%=B.getBookingid() %></td>
                    <td><%=B.getDate()%></td>
                    <td><%=B.getDid()%></td>
                    <td><%=doc_name%></td>
                    <td><%=map[B.getTime_slot()]%></td>
                    <td><%=B.getCategory()%></td>
                </tr>
                </table>
                </div>
                <div id="div3">
                <table>
                <tr style="height: 100px;">
                    <td>Prescription : </td>
                    <td style="width: 700px;"><%=B.getPrescription() %></td>
                </tr>
                </table>
                </div>  	         	
                
                
                
</body>
</html>