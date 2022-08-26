<%@page import="com.pmaharsh.onlineDoctorAppointmentSystem.model.User"%>
<%@page
	import="com.pmaharsh.onlineDoctorAppointmentSystem.service.AuthenticationService"%>
<%@page
	import="com.pmaharsh.onlineDoctorAppointmentSystem.model.Appointment"%>
<%@page import="java.util.ArrayList"%>
<%@page
	import="com.pmaharsh.onlineDoctorAppointmentSystem.service.AppointmentService"%>
<%@page import="com.pmaharsh.onlineDoctorAppointmentSystem.model.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	Doctor d = (Doctor) session.getAttribute("loginDoctor");
	if (d == null) {
		response.sendRedirect("index.jsp");
	}
	%>
	<%
	AppointmentService appointmentService = new AppointmentService();
	ArrayList<Appointment> pastAppointments = appointmentService.getAppointmentsByDoctorUsername(d.getUsername(), true);
	ArrayList<Appointment> futureAppointments = appointmentService.getAppointmentsByDoctorUsername(d.getUsername(), false);
	AuthenticationService authService = new AuthenticationService();
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
				<div class="col-lg-8">
					<div class="card ">
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
										<th>Status</th>
										<th>Datetime</th>
										<th>User Name</th>
										<th>Phone No</th>
										<th>Update Prescription and status</th>
									</tr>
								</thead>

								<%
								for (Appointment a : futureAppointments) {
								%>
								<tr>
									<td><%=a.getId()%></td>
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
									User user = authService.getUserByUsername(a.getUsername());
									%>
									<td><%=user.getName()%></td>
									<td><%=user.getPhone()%></td>
									<td>
										<form action="updatePrescriptionAndStatus" method="post">
											<input type="hidden" name="id" value="<%=a.getId()%>">
											<input type="text" name="prescription"
												value="<%=a.getPrescription()%>"> &nbsp;&nbsp; <label
												for="isCompleted">Completed</label> <input type="checkbox"
												id="isCompleted" name="isCompleted" <%if (a.isStatus()) {%>
												checked <%}%>> &nbsp;&nbsp; <input type="submit" class="btn btn-sm btn-warning"
												value="Update">
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
						<div class="card-header  text-center bg-info text-light">Past
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
										<th>User Name</th>
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
									%><td><%=a.getPrescription()%></td>
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
									User user = authService.getUserByUsername(a.getUsername());
									%>
									<td><%=user.getName()%></td>
									<td><%=user.getPhone()%></td>
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

</body>
</html>