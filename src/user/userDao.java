package user;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;

import doctor.doctor;

public class userDao {
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
	public static int register(user e)throws Exception
	{
		int status=0;  
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("insert into user (name,password,gender,bloodgroup,age,phone,city,state,email) values(?,?,?,?,?,?,?,?,?);");
		ps.setString(1,e.getName());
        ps.setString(2,MD5(e.getPassword()));
        ps.setString(3,e.getGender());
        ps.setString(4,e.getBloodgroup()); 			
        ps.setInt(5,e.getAge());
        ps.setString(6,e.getPhone());
        ps.setString(7,e.getCity());
        ps.setString(8,e.getState());
        ps.setString(9,e.getEmail());
		status = ps.executeUpdate();
		
		con.close();
		return status;
	}
	public static boolean  verify(user e)throws Exception
	{
		int status=0;
		String act_psw = null;
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("select * from user where name=? and password=? and id=?");  
        ps.setString(1,e.getName());
        ps.setString(2,MD5(e.getPassword()) );
        ps.setInt(3, e.getId());
        ResultSet rs=ps.executeQuery();  
        if(rs.next()){    
            return true;    
        }  
        con.close(); 
        return false;
	}
	public static user getUserByid(String id)throws Exception
	{
		user u = new user();
		Connection con=getConnection();
		PreparedStatement ps=con.prepareStatement("select * from user where id=?");  
        ps.setInt(1,Integer.parseInt(id));
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
        	u.setId(rs.getInt(1));  
            u.setName(rs.getString(2));    
            u.setGender(rs.getString(4));  
            u.setBloodgroup(rs.getString(5));
            u.setAge(Integer.parseInt(rs.getString(6)));
            u.setPhone(rs.getString(7));
        }
		return u;
	}
}
