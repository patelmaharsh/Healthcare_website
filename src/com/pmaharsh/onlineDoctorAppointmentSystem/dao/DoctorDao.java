package com.pmaharsh.onlineDoctorAppointmentSystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.pmaharsh.onlineDoctorAppointmentSystem.model.Doctor;

public class DoctorDao {
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
	public Doctor getDoctorByUsername(String username) {
		Doctor d = null;
		try {
			Connection con = getDatabaseConnection();
			if(con==null) {
				System.out.println("database connection issue");
			} else {
				PreparedStatement ps = con.prepareStatement(StaticConfigurations.GET_DOCTOR_BY_USERNAME);
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					d = new Doctor();
					d = fillDoctor(rs, d);
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return d;
	}
	public ArrayList<Doctor> getDoctorByCityCategory(String city, String category){
		ArrayList<Doctor> doctorList = null;
		try {
			Connection con = getDatabaseConnection();
			if(con==null) System.out.println("database connection issue");
			else {
				doctorList = new ArrayList<>();
				PreparedStatement ps = con.prepareStatement(StaticConfigurations.GET_DOCTORS_BY_CITY_CATEGORY);
				ps.setString(1, city);
				ps.setString(2, category);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Doctor d = new Doctor();
					d = fillDoctor(rs, d);
					doctorList.add(d);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return doctorList;
	}
	public Doctor fillDoctor(ResultSet rs, Doctor d) throws SQLException {
		d.setUsername(rs.getString(1));
		d.setName(rs.getString(2));
		d.setPassword(rs.getString(3));
		d.setAge(Integer.parseInt(rs.getString(4)));
		d.setEmail(rs.getString(5));
		d.setPhone(rs.getString(6));
		d.setCity(rs.getString(7));
		d.setLocation(rs.getString(8));
		d.setCategory(rs.getString(9));
		d.setGender(rs.getString(10));
		return d;
	}
	public ArrayList<String> getCategory() {
		ArrayList<String> listCategories = null;
		try {
			Connection con = getDatabaseConnection();
			if(con==null) System.out.println("database connection issue");
			else {
				listCategories = new ArrayList<>();
				PreparedStatement ps = con.prepareStatement(StaticConfigurations.GET_CATEGORIES);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					listCategories.add(rs.getString(1));
				}
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return listCategories;
	}
}
