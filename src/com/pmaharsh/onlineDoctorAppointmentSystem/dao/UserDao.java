package com.pmaharsh.onlineDoctorAppointmentSystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.pmaharsh.onlineDoctorAppointmentSystem.model.User;
import com.pmaharsh.onlineDoctorAppointmentSystem.service.AuthenticationService;

public class UserDao {
	
	public Connection getDatabaseConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(StaticConfigurations.urlString,StaticConfigurations.jdbcUsername,StaticConfigurations.jdbcPassword);
			return con;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int addUser(User u) {
		int status = 0;
		try {
			Connection con = getDatabaseConnection();
			if(con==null) {
				System.out.println("user not added. some issue in database connection");
			} else {
				PreparedStatement st = con.prepareStatement(StaticConfigurations.SAVE_USER);
				st.setString(1, u.getName());
				st.setString(2, u.getUsername());
				st.setString(3, StaticConfigurations.passwordHashing(u.getPassword()));
				st.setInt(4, u.getAge());
				st.setString(5, u.getEmail());
				st.setString(6, u.getPhone());
				st.setString(7, u.getAddress());
				st.setString(8, u.getCity());
				status = st.executeUpdate();
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}
	
	public User getUserByUsername(String username) {
		User u = null;
		try {
			Connection con = getDatabaseConnection();
			if(con==null) {
				System.out.println("database connection issue");
			} else {
				PreparedStatement ps = con.prepareStatement(StaticConfigurations.GET_USER_BY_USERNAME);
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					u = new User();
					u.setUsername(rs.getString(1));
					u.setName(rs.getString(2));
					u.setPassword(rs.getString(3));
					u.setAge(Integer.parseInt(rs.getString(4)));
					u.setEmail(rs.getString(5));
					u.setPhone(rs.getString(6));
					u.setAddress(rs.getString(7));
					u.setCity(rs.getString(8));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return u;
	}
	
	
	
	
	
	
	
	
	
	
}
