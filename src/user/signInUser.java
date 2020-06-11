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

@WebServlet("/signInUser")
public class signInUser extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter out=res.getWriter();
		int id;
		String name,psw;
		id=Integer.parseInt(req.getParameter("id"));
		name=req.getParameter("name");
		psw=req.getParameter("psw");
		user e= new user();
		e.setName(name);
		e.setPassword(psw);
		e.setId(id);
		try {
			if(userDao.verify(e))
			{  
		        HttpSession session=req.getSession();  
		        session.setAttribute("id",id);
		        res.sendRedirect("user_page.jsp");
			}
			else
			{  
				out.println("Wrong password or you are not registered");
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return;
	}
}
