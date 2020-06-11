package doctor;
import java.sql.*;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signUpDoctor")
public class signUpDoctor extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		String name,psw,psw_repeat,gender,area,city,state,phone,category,email;
		name=req.getParameter("name");
		psw=req.getParameter("psw");
		psw_repeat=req.getParameter("psw-repeat");
		gender = req.getParameter("gender");
		phone = req.getParameter("phone"); 
		area= req.getParameter("area");
		city = req.getParameter("city");
		state = req.getParameter("state");
		category = req.getParameter("category");
		email = req.getParameter("email");
		if(psw.equals(psw_repeat)) {
			doctor e=new doctor();
			e.setName(name);			
			e.setPassword(psw);	
			e.setGender(gender);
			e.setPhone(phone);
			e.setCity(city);
			e.setState(state);
			e.setArea(area);
			e.setCategory(category);
			e.setEmail(email);
			try {
			doctorDao.register(e);
			RequestDispatcher rd=req.getRequestDispatcher("/signInDoctor.html");
			}
			catch(Exception exp) {System.out.println("Not Saved "+exp);}
		}
		else
		{  
	        RequestDispatcher rd=req.getRequestDispatcher("/index.jsp");  
	        rd.include(req, res);  
		}
		return;
	}
}
