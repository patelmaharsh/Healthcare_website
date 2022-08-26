package com.pmaharsh.onlineDoctorAppointmentSystem;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AddServlet extends HttpServlet{
	
	public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
		
		int i = Integer.parseInt(req.getParameter("num1"));
		int j = Integer.parseInt(req.getParameter("num2"));
		int k = i+j;
			
		// Writing on WebPage (Not Recommended)
		/*
		 * PrintWriter out = res.getWriter(); 
		 * out.write("result is : "+k);
		 */
		
		// Forward req and res without letting Client know
		/*
		 * req.setAttribute("k", k); RequestDispatcher rd =
		 * req.getRequestDispatcher("/sq"); rd.forward(req, res);
		 */
		
		// 
		/* res.sendRedirect("sq?k="+k); */
		
		// 
		/*
		 * HttpSession session = req.getSession(); 
		 * session.setAttribute("k", k);
		 */
		
		Cookie c = new Cookie("k", k+"");
		res.addCookie(c);
		
		res.sendRedirect("sq");
		
	}
	/*
	 * public void doGet(HttpServletRequest req, HttpServletResponse res) throws
	 * IOException { System.out.println("Inside Get"); int i =
	 * Integer.parseInt(req.getParameter("num1")); int j =
	 * Integer.parseInt(req.getParameter("num2")); int k = i+j;
	 * System.out.println("result is : "+k); }
	 */
	/*
	 * public void doPost(HttpServletRequest req, HttpServletResponse res) throws
	 * IOException { System.out.println("inside Post"); int i =
	 * Integer.parseInt(req.getParameter("num1")); int j =
	 * Integer.parseInt(req.getParameter("num2")); int k = i+j;
	 * System.out.println("result is : "+k); }
	 */
}
