package doctor;


import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

public class doctorDao {
	public static Connection getConnection(){
		Connection con=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try{
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/abc","root","mysql");
		}catch(Exception e){System.out.println(e);}
		return con;
	}
	private static MessageDigest md;
	   public static String MD5(String pass){
	    try {
	        md = MessageDigest.getInstance("MD5");
	        byte[] passBytes = pass.getBytes();
	        md.reset();
	        byte[] digested = md.digest(passBytes);
	        StringBuffer sb = new StringBuffer();
	        for(int i=0;i<digested.length;i++){
	            sb.append(Integer.toHexString(0xff & digested[i]));
	        }
	        return sb.toString();
	    } catch (NoSuchAlgorithmException ex) {
	        System.out.println("Encryption Error");
	    }
	        return null;
	   }
	public static int register(doctor e)throws Exception
	{
		int status=0;  
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("insert into doctor (name,password,gender,area,city,state,phone,category,email) values(?,?,?,?,?,?,?,?,?);");
		ps.setString(1,e.getName());
        ps.setString(2,MD5(e.getPassword()));
        ps.setString(3,e.getGender());
        ps.setString(4,e.getArea());
        ps.setString(5,e.getCity());
        ps.setString(6,e.getState());
        ps.setString(7,e.getPhone());
        ps.setString(8,e.getCategory());
        ps.setString(9,e.getEmail());
		status = ps.executeUpdate();
		ResultSet rs = ps.executeQuery("SELECT MAX(id) AS did FROM doctor");
		rs.next();
		String did =rs.getString("did");
		ps=con.prepareStatement("CREATE TABLE `abc`.`doc_"+did+"` (" + 
				"  `date` DATE NOT NULL,`slot1` TINYINT NULL DEFAULT 0," 
				+ "  `slot2` TINYINT NULL DEFAULT 0,`slot3` TINYINT NULL DEFAULT 0,`slot4` TINYINT NULL DEFAULT 0,"
				+ "`slot5` TINYINT NULL DEFAULT 0,`slot6` TINYINT NULL DEFAULT 0,`slot7` TINYINT NULL DEFAULT 0,"
				+ "`slot8` TINYINT NULL DEFAULT 0,`slot9` TINYINT NULL DEFAULT 0,`slot10` TINYINT NULL DEFAULT 0);");
		ps.execute();
		ps=con.prepareStatement("UPDATE `abc`.`doc_location` SET `area` = '"+ e.getArea()+ "', `city` = '"+ e.getCity()+ "', `state` = '"+ e.getState()
				+ "' WHERE (`did` = '"+ did+ "');");
		con.close();
		return status;
	}
	public static boolean  verify(doctor e)throws Exception
	{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("select * from doctor where name=? and password=? and id=?");  
        ps.setString(1,e.getName());
        ps.setString(2, MD5(e.getPassword()) );
        ps.setInt(3, e.getId());
        ResultSet rs=ps.executeQuery();  
        if(rs.next()){    
            return true;    
        }  
        con.close(); 
        return false;
	}
	public static  ArrayList<doctor> getListByCityCategory(String city,String category)throws Exception 
	{
		Connection con=getConnection();
		ArrayList<doctor> list = new ArrayList<doctor>();
		PreparedStatement ps=con.prepareStatement("select * from doctor where city=? and category=?");  
        ps.setString(1,city);
        ps.setString(2,category);
        ResultSet rs=ps.executeQuery();  
        while(rs.next()){
        	doctor d=new doctor();
        	d.setId(rs.getInt(1));  
            d.setName(rs.getString(2));    
            d.setGender(rs.getString(4));  
            d.setPhone(rs.getString(8));
            d.setCategory(rs.getString(9));
            d.setEmail(rs.getString(10));
            d.setRating(rs.getInt(11));
            d.setHit(rs.getInt(12));
            list.add(d);
        }
        con.close(); 
		return list;
	}
	public static ArrayList<ArrayList<String> > getSlotByDate(String id,String did)throws Exception
	{
		Connection con=getConnection();
		ArrayList<ArrayList<String> > list  = new ArrayList<ArrayList<String> >();
		
		//for today's date
		LocalDateTime  now = LocalDateTime.now(); 
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        String date = now.format(format);
        
        for(int i=0;i<10;i++) {
        	ArrayList<String> day = new ArrayList<String>(11);
        	day.add(date);
        	PreparedStatement ps=con.prepareStatement("select * from doc_"+did+" where date=?");
        	ps.setString(1, date);
        	ResultSet rs=ps.executeQuery();
        	if(rs.next())
        	{
        		for(int j=1;j<=10;j++)
        		{
        		day.add(rs.getString(j));
        		}
        	}
        	else
        	{
        		ps=con.prepareStatement("insert into doc_"+did+" (date) values('"+date+"')");
        		ps.execute();
        		for(int j=1;j<=10;j++)
        		day.add("0");
        	}
        	list.add(day);
        	now=now.plusDays(1);
        	date=now.format(format);
        }
		return list;
	}
	public static void setLocation(String did,String x,String y)throws Exception
	{
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("INSERT INTO `abc`.`doc_location` (`did`, `x`, `y`) VALUES ('"+ did+ "', '"+ x+ "', '"+ y+ "');");
		ps.executeUpdate();
		return;
	}
	public static ArrayList<String> viewLocation(String did)throws Exception
	{
		Connection con=getConnection();
		ArrayList<String>result= new ArrayList<String>();
		PreparedStatement ps=con.prepareStatement("select * from `abc`.`doc_location` where did = ?;");
		ps.setString(1, did);
		ResultSet rs=ps.executeQuery();  
		while(rs.next())
		{
			result.add(rs.getString(1));
			result.add(rs.getString(2));
		}
		ps=con.prepareStatement("select * from `abc`.`doctor` where id = ?;");
		ps.setString(1, did);
		rs=ps.executeQuery();
		while(rs.next())
		{
			result.add(rs.getString(5));
			result.add(rs.getString(6));
			result.add(rs.getString(7));
		}
		return result;
	}
	public static void rateDoctor(String id, String val)throws Exception
	{
		Connection con=getConnection();	
		PreparedStatement ps=con.prepareStatement("UPDATE doctor  SET  rating = rating + '"
				+ val
				+ "' , hit = hit + '1' where id='"
				+ id
				+ "';");  
        ps.executeUpdate();  
        con.close(); 
	}
}
