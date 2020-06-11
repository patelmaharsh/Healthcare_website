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

@WebServlet("/signOutDoctor")
public class signOutDoctor extends HttpServlet{
	protected void doGet(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{	
		PrintWriter out=res.getWriter();
		HttpSession s= req.getSession();
		s.removeAttribute("did");
		s.invalidate();
		out.println("You are successfully logged out!");
		out.close();
		return;
	}
}