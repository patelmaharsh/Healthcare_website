package doctor;
import java.sql.*;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/setLocation")
public class setLocation extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter out=res.getWriter();
		String x=req.getParameter("x");
		String y=req.getParameter("y");
		String did= req.getParameter("did");
		System.out.println(did);
		try {
		doctorDao.setLocation(did,x,y);
		res.sendRedirect("content_doc.jsp");
		}
		catch (Exception e)
		{
			System.out.println("Error while setting location "+e);
		}
		return;
	}
}
