<%@page import="com.pmaharsh.onlineDoctorAppointmentSystem.model.Doctor"%>
<%@page import="com.pmaharsh.onlineDoctorAppointmentSystem.service.AuthenticationService"%>
<%@page import="com.pmaharsh.onlineDoctorAppointmentSystem.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"  errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="bootstrapIncludes.jsp" />
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setHeader("Expires", "0");
		User u = (User)session.getAttribute("loginUser");
		if(u==null){
			response.sendRedirect("index.jsp");
			return;
		} 
	%>
	<%
		String doctorUsername = (String)request.getAttribute("doctorUsername");
		AuthenticationService service = new AuthenticationService();
		Doctor d = service.getDoctorByUsername(doctorUsername);
	%>
	<div>
		<jsp:include page="commonHeader.jsp" />
	</div>
	<div>
		<div class="container-fluid">
			<div class="row mt-4">
				<div class="col-lg-4">
					<div class="card">
						<div class="card-header text-center bg-info text-light">Book Appointment</div>
						<div class="card-body">
							<h6>Doctor Name: <%=d.getName() %></h6>
							<h6>Doctor Category: <%= d.getCategory() %></h6>
							<form class="mt-2 " action="handleAppointment" method="post">
								<div class="form-group row">
									<label for="datetime" class="col-sm-4 col-form-label-sm">Appointment (Time and Date)</label>
									<div class="col-sm-8">
										<input type="datetime-local" class="form-control form-control-sm"
											name="datetime" id="datetime" placeholder="DateTime">
									</div>
								</div>
								<input type="hidden" name="username" value="<%=u.getUsername() %>">
								<input type="hidden" name="doctorUsername" value="<%=d.getUsername() %>">
								<input type="submit" class="btn btn-sm btn-warning" value="Book">
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	
</body>
</html>