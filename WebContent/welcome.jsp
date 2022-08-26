<%@page
	import="com.pmaharsh.onlineDoctorAppointmentSystem.service.AuthenticationService"%>
<%@page
	import="com.pmaharsh.onlineDoctorAppointmentSystem.model.Appointment"%>
<%@page
	import="com.pmaharsh.onlineDoctorAppointmentSystem.service.AppointmentService"%>
<%@page import="com.pmaharsh.onlineDoctorAppointmentSystem.model.Doctor"%>
<%@page import="java.util.ArrayList"%>
<%@page
	import="com.pmaharsh.onlineDoctorAppointmentSystem.service.DoctorService"%>
<%@page import="com.pmaharsh.onlineDoctorAppointmentSystem.model.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8" errorPage="error.jsp"%>
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
	User u = (User) session.getAttribute("loginUser");
	if (u == null) {
		response.sendRedirect("index.jsp");
		return;
	}
	%>
	<%
	DoctorService doctorService = new DoctorService();
	ArrayList<String> listCategory = doctorService.getCategories();
	String city = request.getParameter("city");
	String category = request.getParameter("category");
	ArrayList<Doctor> doctorList = null;
	if (city != null && category != null) {
		doctorList = doctorService.getDoctorByCityCategory(city, category);
	}
	AuthenticationService authService = new AuthenticationService();
	AppointmentService appointmentService = new AppointmentService();
	ArrayList<Appointment> pastAppointments = appointmentService.getAppointmentsByUsername(u.getUsername(), true);
	ArrayList<Appointment> futureAppointments = appointmentService.getAppointmentsByUsername(u.getUsername(), false);
	if (pastAppointments.size() == 0)
		pastAppointments = null;
	if (futureAppointments.size() == 0)
		futureAppointments = null;
	%>
	<div>
		<jsp:include page="commonHeader.jsp" />
	</div>
	<div>
		<div class="container-fluid">
			<div class="row mt-4">
				<div class="col-lg-6">
					<div class="card mx-auto w-50">
						<div class="card-header text-center bg-info text-light">Search
							Doctor</div>
						<div class="card-body">
							<form action="welcome.jsp" method="post">
								<div class="form-group row">
									<label for="city" class="col-sm-4 col-form-label-sm">City</label>
									<div class="col-sm-8">
										<input type="text" class="form-control form-control-sm"
											name="city" id="city" placeholder="City">
									</div>
								</div>
								<div class="form-group row">
									<label for="category" class="col-sm-4 col-form-label-sm">Category</label>
									<div class="col-sm-8">
										<select class="form-control form-control-sm" name="category" id="category"
											>
											<%
											for (String c : listCategory) {
											%>
											<option value=<%=c%>><%=c%></option>
											<%
											}
											%>
										</select>
									</div>
								</div>
								<input class="btn btn-info btn-sm" type="submit" value="Search" />
							</form>
						</div>
					</div>
					<div class="card mt-4">
						<div class="card-header text-center bg-info text-light">Doctor's
							List</div>
						<div class="card-body">
							<%
							if (doctorList == null) {
							%>
							<h6>Search doctor by city and category</h6>
							<%
							} else if (doctorList.size() == 0) {
							%>
							<h6>No doctor found</h6>
							<%
							} else {
							%>

							<table class="table table-bordered table-sm table-hover">
								<thead>
									<tr>
										<th>Name</th>
										<th>Gender</th>
										<th>Phone</th>
										<th>Email</th>
										<th>City</th>
										<th>Category</th>
										<th>Action</th>
									</tr>
								</thead>

								<%
								for (Doctor d : doctorList) {
								%>
								<tr>
									<td><%=d.getName()%></td>
									<td><%=d.getGender()%></td>
									<td><%=d.getPhone()%></td>
									<td><%=d.getEmail()%></td>
									<td><%=d.getCity()%></td>
									<td><%=d.getCategory()%></td>
									<td>
										<form action="bookAppointment" method="post">
											<input type="hidden" name="doctorUsername"
												value="<%=d.getUsername()%>"> <input type="submit"
												class="btn btn-warning btn-sm" value="Book">
										</form>
									</td>
								</tr>
								<%
								}
								%>
							</table>
							<%
							}
							%>
						</div>
					</div>
				</div>
				<div class="col-lg-6">
					<div class="card">
						<div class="card-header text-center bg-info text-light">Future
							Appointments</div>
						<div class="card-body">
							<%
							if (futureAppointments == null) {
							%>
							<h6>No future appointments found</h6>
							<%
							} else {
							%>
							<table class="table table-bordered table-sm table-hover">
								<thead>
									<tr>
										<th>ID</th>
										<th>Prescription</th>
										<th>Status</th>
										<th>Datetime</th>
										<th>DoctorName</th>
										<th>Phone No</th>
										<th>Action</th>
									</tr>
								</thead>

								<%
								for (Appointment a : futureAppointments) {
								%>
								<tr>
									<td><%=a.getId()%></td>
									<%
									if (a.getPrescription() != null && a.getPrescription().length() > 0) {
									%><td class="text-truncate"><%=a.getPrescription()%></td>
									<%
									} else {
									%><td>Not Given</td>
									<%
									}
									%>
									<%
									if (a.isStatus()) {
									%><td>completed</td>
									<%
									} else {
									%><td>pending</td>
									<%
									}
									%>
									<td><%=a.getDate()%></td>
									<%
									Doctor doc = authService.getDoctorByUsername(a.getDoctorUsername());
									%>
									<td><%=doc.getName()%></td>
									<td><%=doc.getPhone()%></td>
									<td>
										<form action="deleteAppointment" method="post">
											<input type="hidden" name="appointmentId"
												value="<%=a.getId()%>"> <input type="submit"
												class="btn btn-danger btn-sm" value="Delete">
										</form>
									</td>
								</tr>
								<%
								}
								%>
							</table>
							<%
							}
							%>
						</div>
					</div>
					<div class="card mt-4">
						<div class="card-header text-center bg-info text-light">Past
							Appointments</div>
						<div class="card-body">
							<%
							if (pastAppointments == null) {
							%>
							<h6>No past appointments found</h6>
							<%
							} else {
							%>
							<table class="table table-bordered table-sm table-hover">
								<thead>
									<tr>
										<th>ID</th>
										<th>Prescription</th>
										<th>Status</th>
										<th>Datetime</th>
										<th>DoctorName</th>
										<th>Phone No</th>
									</tr>
								</thead>

								<%
								for (Appointment a : pastAppointments) {
								%>
								<tr>
									<td><%=a.getId()%></td>
									<%
									if (a.getPrescription() != null && a.getPrescription().length() > 0) {
									%><td class="text-truncate"><%=a.getPrescription()%></td>
									<%
									} else {
									%><td>Not Given</td>
									<%
									}
									%>
									<%
									if (a.isStatus()) {
									%><td>completed</td>
									<%
									} else {
									%><td>pending</td>
									<%
									}
									%>
									<td><%=a.getDate()%></td>
									<%
									Doctor doc = authService.getDoctorByUsername(a.getDoctorUsername());
									%>
									<td><%=doc.getName()%></td>
									<td><%=doc.getPhone()%></td>
								</tr>
								<%
								}
								%>
							</table>
							<%
							}
							%>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	<%-- <strong>Welcome <%=u.getName()%></strong>&nbsp;&nbsp;&nbsp; --%>
	<%-- <form action="welcome.jsp" method="post">
		City : <input id="city" type="text" name="city">
		&nbsp;&nbsp;&nbsp; <label for="categories">Category : </label> <select
			name="category" id="categories">
			<%
			for (String c : listCategory) {
			%>
			<option value=<%=c%>><%=c%></option>
			<%
			}
			%>
		</select> &nbsp;&nbsp;&nbsp; <input type="submit" value="Search">
	</form>

	&nbsp;&nbsp;&nbsp; --%>
	<!-- <form action="logout">
		<input type="submit" value="Logout">
	</form>
	<hr /> -->




</body>
</html>