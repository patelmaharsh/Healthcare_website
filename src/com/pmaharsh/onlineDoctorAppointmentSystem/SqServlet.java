package com.pmaharsh.onlineDoctorAppointmentSystem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SqServlet extends HttpServlet {

	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		// When something is stored in Request
		/* int k = (int) req.getAttribute("k"); */
		
		//
		/* int k = Integer.parseInt(req.getParameter("k")); */
		
		// 
		/*
		 * HttpSession session = req.getSession(); int k = (int)
		 * session.getAttribute("k");
		 */
		
		int k=0;
		Cookie[] cookies = req.getCookies();
		for(Cookie c: cookies) {
			if(c.getName().equals("k")) {
				k = Integer.parseInt(c.getValue());
			}
		}
		
		ServletConfig config = getServletConfig();
		String name = config.getInitParameter("name");
		System.out.println("");
		
		PrintWriter out = res.getWriter();
		out.write("result is: " + k * k);
		out.write("\n");
		out.write("name : "+name);
		
		// Call MyServlet Class to demonstrate ServletContext
		/*
		 * RequestDispatcher rd = req.getRequestDispatcher("/myservlet");
		 * rd.forward(req, res);
		 */

	}
}
