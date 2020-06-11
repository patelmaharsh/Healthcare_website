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

@WebServlet("/signInDoctor")
public class signInDoctor extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		PrintWriter out=res.getWriter();
		int id;
		String name,psw;
		id=Integer.parseInt(req.getParameter("id"));
		name=req.getParameter("name");
		psw=req.getParameter("psw");
		doctor e= new doctor();
		e.setName(name);
		e.setPassword(psw);
		e.setId(id);
		try {
			if(doctorDao.verify(e))
			{  
		        HttpSession session=req.getSession();  
		        session.setAttribute("did",id);
		        res.sendRedirect("doctor_page.jsp");
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
