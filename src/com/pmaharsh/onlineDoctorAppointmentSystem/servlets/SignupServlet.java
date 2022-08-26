package com.pmaharsh.onlineDoctorAppointmentSystem.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pmaharsh.onlineDoctorAppointmentSystem.model.User;
import com.pmaharsh.onlineDoctorAppointmentSystem.service.AuthenticationService;

@WebServlet("/signup")
public class SignupServlet extends HttpServlet{
	@Override
	public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		Integer age = Integer.parseInt(req.getParameter("age"));
		String phone = req.getParameter("phone");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String city = req.getParameter("city");
		
		if(true) { // check for password match
			AuthenticationService service = new AuthenticationService();
			User u = new User();
			u.setName(name);
			u.setUsername(username);
			u.setPassword(password);
			u.setAge(age);
			u.setPhone(phone);
			u.setEmail(email);
			u.setAddress(address);
			u.setCity(city);
			service.saveUser(u);
		}
		
		
		res.sendRedirect("index.jsp");
	}
}
