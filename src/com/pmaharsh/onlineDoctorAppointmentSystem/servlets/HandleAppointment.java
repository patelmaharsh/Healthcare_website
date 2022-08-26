package com.pmaharsh.onlineDoctorAppointmentSystem.servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pmaharsh.onlineDoctorAppointmentSystem.service.AppointmentService;


@WebServlet("/handleAppointment")
public class HandleAppointment extends HttpServlet {
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String datetime = req.getParameter("datetime");
		String username = req.getParameter("username");
		String doctorUsername = req.getParameter("doctorUsername");
		AppointmentService service = new AppointmentService();
		int status = service.bookAppointment(datetime, username, doctorUsername);
		if(status>0) {
			res.sendRedirect("welcome.jsp");
		} else {
			req.setAttribute("doctorUsername", doctorUsername);
			RequestDispatcher rd = req.getRequestDispatcher("bookAppointment.jsp");
			rd.forward(req, res);
		}
		
	}

}
