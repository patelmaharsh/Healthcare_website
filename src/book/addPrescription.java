package book;
import java.sql.*;
import java.util.ArrayList;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import doctor.doctor;
import doctor.doctorDao;
@WebServlet("/addPrescription")
public class addPrescription extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter out = res.getWriter();
		String bid=req.getParameter("bid");
		String pre = req.getParameter("prescription");						//	System.out.println(pre);
		int status=1;
		try {
			bookDao.addPrescription(bid , pre);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(status==1) {
		out.println("Successfully Booked your slot");
		res.sendRedirect("doctorViewBooking.jsp");												// to be assigned
		}
		else
		{
			out.println("Sorry you're a bit late!!!\n It is already booked");
		}
		return;
	}
}
