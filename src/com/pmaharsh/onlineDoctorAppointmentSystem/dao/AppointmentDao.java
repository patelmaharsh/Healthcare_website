package com.pmaharsh.onlineDoctorAppointmentSystem.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.pmaharsh.onlineDoctorAppointmentSystem.model.Appointment;

public class AppointmentDao {
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
	public int saveAppointment(Appointment a) {
		int status = 0;
		try {
			Connection con = getDatabaseConnection();
			if(con==null) System.out.println("appointment not booked. some issue in database connection");
			else {
				PreparedStatement ps = con.prepareStatement(StaticConfigurations.SAVE_APPOINTMENT);
				ps.setInt(1, a.getId());
				ps.setString(2, a.getCategory());
				ps.setInt(3, a.isStatus()?1:0);
				ps.setString(4, a.getDate());
				ps.setString(5, a.getUsername());
				ps.setString(6, a.getDoctorUsername());
				status = ps.executeUpdate();
			}
			
		} catch(Exception e) {
			System.out.println("Exception occured while executing query");
			e.printStackTrace();
		}
		return status;
	}
	public int getMaxId() {
		int id = 0;
		try {
			Connection con = getDatabaseConnection();
			if(con==null) System.out.println("max id from "+StaticConfigurations.APPOINTMENT_TABLE+" is not fetched. some issue in database connection");
			else {
				PreparedStatement ps = con.prepareStatement(StaticConfigurations.GET_MAX_ID_COUNT);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					id = Integer.parseInt(rs.getString(1));
				}
			}
		} catch (Exception e) {
			System.out.println("Exception occured while executing query");
		}
		return id;
	}
	public ArrayList<Appointment> getAppointmentByUsername(String username, String datetime, boolean isPast){
		ArrayList<Appointment> appointments = new ArrayList<>();
		try {
			Connection con = getDatabaseConnection();
			if(con==null) System.out.println("some issue in database connection while fetching appointments");
			else {
				PreparedStatement ps = con.prepareStatement(isPast?StaticConfigurations.GET_PAST_APPOINTMENT_BY_USERNAME:StaticConfigurations.GET_FUTURE_APPOINTMENT_BY_USERNAME);
				ps.setString(1, username);
				ps.setString(2, datetime);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Appointment a = new Appointment();
					a = fillAppointment(a,rs);
					appointments.add(a);
				}
			}
		} catch(Exception e) {
			System.out.println("exception occured executing query while fetching appointments");
			e.printStackTrace();
		}
		
		return appointments;
	}
	public ArrayList<Appointment> getAppointmentByDoctorUsername(String username, String datetime, boolean isPast){
		ArrayList<Appointment> appointments = new ArrayList<>();
		try {
			Connection con = getDatabaseConnection();
			if(con==null) System.out.println("some issue in database connection while fetching appointments");
			else {
				PreparedStatement ps = con.prepareStatement(isPast?StaticConfigurations.GET_PAST_APPOINTMENT_BY_DOCTORUSERNAME:StaticConfigurations.GET_FUTURE_APPOINTMENT_BY_DOCTORUSERNAME);
				ps.setString(1, username);
				ps.setString(2, datetime);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Appointment a = new Appointment();
					a = fillAppointment(a,rs);
					appointments.add(a);
				}
			}
		} catch(Exception e) {
			System.out.println("exception occured executing query while fetching appointments");
			e.printStackTrace();
		}
		
		return appointments;
	}
	public int updatePrescriptionAndStatus(String prescription, boolean status, int id) {
		int result = 0;
		try {
			Connection con = getDatabaseConnection();
			if(con==null) System.out.println("some issue in database connection while updating appointment");
			else {
				PreparedStatement ps = con.prepareStatement(StaticConfigurations.UPDATE_PRESCRIPTION_STATUS);
				ps.setString(1, prescription);
				ps.setInt(2, status?1:0);
				ps.setInt(3, id);
				result = ps.executeUpdate();
			}
		} catch(Exception e) {
			System.out.println("exception occured executing query while updating appointment");
			e.printStackTrace();
		}
		return result;
	}
	
	public int deleteAppointment(int appointmentId) {
		int status = 0;
		try {
			Connection con = getDatabaseConnection();
			if(con==null) System.out.println("some issue in database connection while deleting appointment");
			else {
				PreparedStatement ps = con.prepareStatement(StaticConfigurations.DELETE_APPOINTMENT);
				ps.setInt(1, appointmentId);
				status = ps.executeUpdate();
			}
		} catch(Exception e) {
			System.out.println("exception occured executing query while deleting appointment");
			e.printStackTrace();
		}
		
		return status;
	}
	
	public Appointment fillAppointment(Appointment a, ResultSet rs) throws SQLException {
		a.setId(rs.getInt(1));
		a.setPrescription(rs.getString(2));
		a.setCategory(rs.getString(3));
		a.setStatus((rs.getInt(4)>0)?true:false);
		a.setDate(rs.getString(5));
		a.setTimeSlot(rs.getInt(6));
		a.setUsername(rs.getString(7));
		a.setDoctorUsername(rs.getString(8));
		return a;
	}
}
