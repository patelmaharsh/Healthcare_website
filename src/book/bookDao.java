package book;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;  
import javax.servlet.annotation.WebServlet;  
import javax.servlet.http.HttpServlet;  
import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import javafx.util.Pair; 
import book.book;

public class bookDao {
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
	
	public static ArrayList<ArrayList<String>> getBooking(String did) throws SQLException
	{
		Connection con=getConnection();
		ArrayList<ArrayList<String> > result = new ArrayList<ArrayList<String> >();
		
		//for today's date
		LocalDateTime  now = LocalDateTime.now(); 
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        String date = now.format(format);
        
        PreparedStatement ps=con.prepareStatement("select * from booking where did=? and status='0' and date >='"+date+"' ORDER BY date ASC");
        ps.setString(1, did);													//System.out.println(date);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	ArrayList<String> booking = new ArrayList<String>(8);
        	for(int j=1;j<=8;j++)
    		{
    		booking.add(rs.getString(j));										//System.out.println(rs.getString(j));
    		}
        	result.add(booking);
        }
		return result;
	}
	
	public static int processBooking(String id,String did, String slot,String category) throws Exception
	{
		int status;
		String map[] =new String[]{"","8:00","9:00", "10:00","11:00" ,"14:00","15:00","16:00","17:00","18:00","19:00"};
		Connection con = getConnection();
		String date = slot.substring(0,10);
		int c = (int)slot.charAt(10)-(int)'0';
		PreparedStatement ps=con.prepareStatement("SELECT bookingid  FROM abc.booking where time_slot='"+ c+ "' and date='"+ date+ "';");		
		ResultSet rs = ps.executeQuery();
		if(rs.next()==false) {
		ps=con.prepareStatement("insert into booking (id,did,date,time_slot,status,prescription,category) values(?,?,?,?,?,?,?);");
		ps.setString(1, id);
		ps.setString(2, did);
		ps.setString(3, date);
		ps.setInt(4, c);
		ps.setInt(5, 0);
		ps.setString(6,null);
		ps.setString(7,category);			
		ps.executeUpdate();
		rs = ps.executeQuery("SELECT MAX(bookingid) AS bookingid FROM booking");
		rs.next();
		String bookid =rs.getString("bookingid");
		ps=con.prepareStatement("UPDATE `abc`.`doc_"+ did +"` SET `slot"+c+ "` = '"+ bookid+ "' WHERE (`date` = '"+ date+ "');");
		ps.executeUpdate();
		status=1;
		}
		else
		{
			status=0;
		}
		return status;
	}
	public static void updateBookDb(String bid)throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps=con.prepareStatement("UPDATE `abc`.`booking` SET `status` = '1' WHERE (`bookingid` = '"+ bid+ "');");
		ps.executeUpdate();
		return ;
	}
	public static void addPrescription(String bid , String pre)throws Exception
	{
		Connection con = getConnection();
		PreparedStatement ps=con.prepareStatement("UPDATE `abc`.`booking` SET `prescription` = '" + pre + "' WHERE (`bookingid` = '" + bid + "');");
		ps.executeUpdate();
		return ;
	}
	public static ArrayList< Pair<book,String> > viewPastRecord(String id) throws SQLException
	{
		Connection con=getConnection();
		ArrayList< Pair<book,String> >temp = new ArrayList< Pair<book,String> >();
		//for today's date
		LocalDateTime  now = LocalDateTime.now(); 
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        String date = now.format(format);
        
        PreparedStatement ps=con.prepareStatement("select * from booking where id=? and status='1'  ORDER BY date DESC");
        ps.setString(1, id);				////and date <='"+date+"'
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	book B = new book();
        	for(int j=1;j<=8;j++)
    		{								
        		B.setBookingid( Integer.parseInt(rs.getString(1)) );
        		B.setId( Integer.parseInt(rs.getString(2)) );
        		B.setDid( Integer.parseInt(rs.getString(3)) );
        		B.setDate( rs.getString(4) );
        		B.setTime_slot( Integer.parseInt(rs.getString(5)) );
        		B.setStatus( Integer.parseInt(rs.getString(6)) );
        		B.setPrescription( rs.getString(7) );
        		B.setCategory( rs.getString(8) );
    		}
        	int did=B.getDid();
        	PreparedStatement psx=con.prepareStatement("SELECT name FROM abc.doctor where id="+did+ ";");
        	ResultSet rsx=psx.executeQuery();
        	rsx.next();
        	String name = rsx.getString(1);
        	temp.add(new Pair<book,String>(B,name));
        }
		return temp;
	}
	public static ArrayList< Pair<book,String> > viewFutureRecord(String id) throws SQLException
	{
		Connection con=getConnection();
		ArrayList< Pair<book,String> >temp = new ArrayList< Pair<book,String> >();
		//for today's date
		LocalDateTime  now = LocalDateTime.now(); 
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        String date = now.format(format);
        
        PreparedStatement ps=con.prepareStatement("select * from booking where id=? and status='0' and date >='"+date+"' ORDER BY date ASC");
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
        	book B = new book();
        	for(int j=1;j<=8;j++)
    		{
        		B.setBookingid( Integer.parseInt(rs.getString(1)) );
        		B.setId( Integer.parseInt(rs.getString(2)) );
        		B.setDid( Integer.parseInt(rs.getString(3)) );
        		B.setDate( rs.getString(4) );
        		B.setTime_slot( Integer.parseInt(rs.getString(5)) );
        		B.setStatus( Integer.parseInt(rs.getString(6)) );
        		B.setPrescription( rs.getString(7) );
        		B.setCategory( rs.getString(8) );
    		}
        	int did=B.getDid();
        	PreparedStatement psx=con.prepareStatement("SELECT name FROM abc.doctor where id="+did+ ";");
        	ResultSet rsx=psx.executeQuery();
        	rsx.next();
        	String name = rsx.getString(1);
        	temp.add(new Pair<book,String>(B,name));
        }
		return temp;
	}
	public static Pair<book,String> viewRecord(String bookingid)throws SQLException
	{
		book B = new book();
		Connection con=getConnection();
		Pair<book,String> temp;
		//for today's date
		LocalDateTime  now = LocalDateTime.now(); 
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        String date = now.format(format);
        
        PreparedStatement ps=con.prepareStatement("select * from booking where bookingid=?");
        ps.setString(1, bookingid);
        ResultSet rs = ps.executeQuery();
        rs.next();
        for(int j=1;j<=8;j++)
    	{
        		B.setBookingid( Integer.parseInt(rs.getString(1)) );
        		B.setId( Integer.parseInt(rs.getString(2)) );
        		B.setDid( Integer.parseInt(rs.getString(3)) );
        		B.setDate( rs.getString(4) );
        		B.setTime_slot( Integer.parseInt(rs.getString(5)) );
        		B.setStatus( Integer.parseInt(rs.getString(6)) );
        		B.setPrescription( rs.getString(7) );
        		B.setCategory( rs.getString(8) );
    	}
        int did=B.getDid();
        PreparedStatement psx=con.prepareStatement("SELECT name FROM abc.doctor where id="+did+ ";");
        ResultSet rsx=psx.executeQuery();
        rsx.next();
        String name = rsx.getString(1);
        temp = new Pair<book,String>(B,name);
		return temp;
	}
	public static int cancelBook(String id,String bid,String date_book,String slot,String did)throws Exception
	{
		int status=-1;
		Connection con=getConnection();
		LocalDateTime  now = LocalDateTime.now(); 
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
        String date = now.format(format);
        
        PreparedStatement ps=con.prepareStatement("delete from abc.booking where bookingid="
        		+ bid
        		+ " and date>'"+date+"';",Statement.RETURN_GENERATED_KEYS);
        int rowAffected = ps.executeUpdate();
        if(rowAffected>0)
        {
        	ps=con.prepareStatement("update abc.doc_"
        			+ did
        			+ " set slot"
        			+ slot
        			+ "=0 where date="
        			+ date_book
        			+ ";");
        	return 1;
        }
		return status;
	}
}
