package user;
import java.sql.*;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/signOut")
public class signOutUser extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{	
		PrintWriter out=res.getWriter();
		HttpSession s= req.getSession();
		s.removeAttribute("id");
		s.removeAttribute("did");
		s.invalidate();
		res.sendRedirect("index.jsp");
		return;
	}
}