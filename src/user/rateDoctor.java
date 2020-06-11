package user;
import doctor.doctorDao;
import java.sql.*;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/rateDoctor")
public class rateDoctor extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter out=res.getWriter();
		String val,did;		
		val=req.getParameter("val");
		did = req.getParameter("did");
		try {
			doctorDao.rateDoctor(did,val);
		    res.sendRedirect("user_page.jsp");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return;
	}
}
