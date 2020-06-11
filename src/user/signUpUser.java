package user;
import java.sql.*;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signUpUser")
public class signUpUser extends HttpServlet{
	protected void doPost(HttpServletRequest req,HttpServletResponse res) throws IOException, ServletException
	{
		String name,psw,psw_repeat,bloodgroup,gender,phone,city,state,email;
		name=req.getParameter("name");
		psw=req.getParameter("psw");
		psw_repeat=req.getParameter("psw-repeat");
		int age = Integer.parseInt(req.getParameter("age"));
		bloodgroup = req.getParameter("bloodgroup");
		gender = req.getParameter("gender");
		phone = req.getParameter("phone"); 
		city = req.getParameter("city");
		state = req.getParameter("state");
		email = req.getParameter("email");
		if(psw.equals(psw_repeat)) {
			user e=new user();
			e.setName(name);			
			e.setPassword(psw);
			e.setAge(age);
			e.setBloodgroup(bloodgroup);		
			e.setGender(gender);
			e.setPhone(phone);
			e.setCity(city);
			e.setState(state);
			e.setEmail(email);
			try {
			userDao.register(e);}
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
