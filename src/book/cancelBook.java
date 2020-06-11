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
@WebServlet("/cancelBook")
public class cancelBook extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter out = res.getWriter();
		String id=req.getParameter("id");
		String bid=req.getParameter("bid");
		String did=req.getParameter("did");
		String date=req.getParameter("date_book");
		String slot=req.getParameter("slot");
		int status=0;
		try {
			status= bookDao.cancelBook(id, bid, date, slot, did);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(status==1) {
		out.println("Successfully cancelled your slot");
		res.sendRedirect("user_page.jsp");												
		}
		else
		{
			out.println("Sorry you can't cancel now!!");
		}
		return;
	}
}
