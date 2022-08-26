package com.pmaharsh.onlineDoctorAppointmentSystem.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pmaharsh.onlineDoctorAppointmentSystem.service.AppointmentService;

@WebServlet("/deleteAppointment")
public class DeleteAppointment extends HttpServlet {
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Integer appointmentId = Integer.parseInt(req.getParameter("appointmentId"));
		if(appointmentId==null) System.out.println("please send valid appointmentId");
		else {
			AppointmentService service = new AppointmentService();
			int status = service.deleteAppointment(appointmentId);
			if(status==0) {
				System.out.println("appointment not deleted");
			} else System.out.println("appointment deleted with id "+appointmentId);
		}
		res.sendRedirect("welcome.jsp");
	}

}
