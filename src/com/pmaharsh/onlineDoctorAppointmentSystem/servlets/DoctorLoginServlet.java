package com.pmaharsh.onlineDoctorAppointmentSystem.servlets;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pmaharsh.onlineDoctorAppointmentSystem.dao.StaticConfigurations;
import com.pmaharsh.onlineDoctorAppointmentSystem.model.Doctor;
import com.pmaharsh.onlineDoctorAppointmentSystem.service.AuthenticationService;

@WebServlet("/doctorlogin")
public class DoctorLoginServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		AuthenticationService authService = new AuthenticationService();
		if(username.trim().length()==0 || password.trim().length()<8) System.out.println("problem in username/password");
		Doctor d = authService.getDoctorByUsername(username);
		try {
			if(d!=null && d.getPassword().compareTo(StaticConfigurations.passwordHashing(password))==0) {
				HttpSession session = req.getSession();
				session.setAttribute("loginDoctor", d);
				res.sendRedirect("doctorhome.jsp");
			} else {
				System.out.println("invalid credentials");
				res.sendRedirect("index.jsp");
			}
		} catch (NoSuchAlgorithmException e) {
			System.out.println("exception while converting password hashing");
			e.printStackTrace();
		} 
	}
}
