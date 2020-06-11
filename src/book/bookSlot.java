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
@WebServlet("/bookSlot")
public class bookSlot extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter out = res.getWriter();
		HttpSession sesn=req.getSession();
		String id=req.getParameter("id");
		String did=req.getParameter("did");
		String slot =req.getParameter("slot");
		String category = req.getParameter("category");
		int status=0;
		try {
			status= bookDao.processBooking(id, did, slot,category);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(status==1) {
		out.println("Successfully Booked your slot");
		res.sendRedirect("content.jsp");												// to be assigned
		}
		else
		{
			out.println("Sorry you're a bit late!!!\n It is already booked");
		}
		return;
	}
}
