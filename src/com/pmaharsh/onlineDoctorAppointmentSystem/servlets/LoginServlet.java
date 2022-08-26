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
import com.pmaharsh.onlineDoctorAppointmentSystem.model.User;
import com.pmaharsh.onlineDoctorAppointmentSystem.service.AuthenticationService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		AuthenticationService authService = new AuthenticationService();
		if(username.trim().length()==0 || password.trim().length()<8) System.out.println("problem in username/password");
		User u = authService.getUserByUsername(username);
		try {
			if(u!=null && u.getPassword().compareTo(StaticConfigurations.passwordHashing(password))==0) {
				HttpSession session = req.getSession();
				session.setAttribute("loginUser", u);
				res.sendRedirect("welcome.jsp");
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
