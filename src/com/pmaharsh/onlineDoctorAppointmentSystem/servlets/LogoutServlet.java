package com.pmaharsh.onlineDoctorAppointmentSystem.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		System.out.println("Inside logout servlet");
		
		HttpSession session = req.getSession();
		if(session.getAttribute("loginUser")==null && session.getAttribute("loginDoctor")==null) {
			res.sendRedirect("index.jsp");
			return;
		} 
		if(session.getAttribute("loginUser")!=null || session.getAttribute("loginDoctor")!=null) {
			session.removeAttribute("loginUser");
			session.removeAttribute("loginDoctor");
			session.invalidate();
			res.sendRedirect("logout.jsp");
		}
	}
}
