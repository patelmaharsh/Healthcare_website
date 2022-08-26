package com.pmaharsh.onlineDoctorAppointmentSystem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MyServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException {

		// ServletContext Example
		/*
		 * ServletContext context = getServletContext(); String hospitalName =
		 * context.getInitParameter("hospitalName");
		 */

		// Printing ServletConfig Parameter
		/*
		 * PrintWriter out = res.getWriter(); out.write("name : "+hospitalName);
		 */

		PrintWriter out = res.getWriter();
		int uid = Integer.parseInt(req.getParameter("uid"));
		String uname = req.getParameter("uname");
		out.println("Welcome " + uname);
	}
}
