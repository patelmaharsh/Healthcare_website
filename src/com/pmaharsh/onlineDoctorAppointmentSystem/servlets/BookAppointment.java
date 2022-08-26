package com.pmaharsh.onlineDoctorAppointmentSystem.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bookAppointment")
public class BookAppointment extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("inside bookAppointment servlet");
		String doctorUsername = req.getParameter("doctorUsername");
		if(doctorUsername!=null) {
			req.setAttribute("doctorUsername", doctorUsername);
			RequestDispatcher rd = req.getRequestDispatcher("bookAppointment.jsp");
			rd.forward(req, res);
		} else {
			res.sendRedirect("welcome.jsp");
		}
	}
}
