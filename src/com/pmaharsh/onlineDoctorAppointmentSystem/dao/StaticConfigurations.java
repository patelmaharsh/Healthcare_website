package com.pmaharsh.onlineDoctorAppointmentSystem.dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class StaticConfigurations {
	public static final String urlString = "jdbc:mysql://localhost:3306/onlinehospitalappointment";
	public static final String jdbcUsername = "root";
	public static final String jdbcPassword = "mysql";
	
	public static final String DOCTOR_TABLE = "doctor";
	public static final String USER_TABLE = "user";
	public static final String APPOINTMENT_TABLE = "appointment";
	
	public static final String SAVE_USER = "insert into "+USER_TABLE+"(username,name,password,age,email,phone,address,city) values(?,?,?,?,?,?,?,?)";
	public static final String GET_USER_BY_USERNAME = "select * from "+USER_TABLE+" where username = ?";
	public static final String GET_DOCTOR_BY_USERNAME = "select * from "+DOCTOR_TABLE+" where username = ?";
	public static final String GET_DOCTORS_BY_CITY_CATEGORY = "select * from "+DOCTOR_TABLE+" where city = ? and category=?";
	public static final String GET_CATEGORIES = "select distinct category from "+DOCTOR_TABLE;
	public static final String GET_MAX_ID_COUNT = "select max(id) from "+APPOINTMENT_TABLE;
	public static final String SAVE_APPOINTMENT = "insert into "+APPOINTMENT_TABLE+" (id,category,status,date,username,doctorUsername) values(?,?,?,?,?,?)";
	public static final String GET_FUTURE_APPOINTMENT_BY_USERNAME = "select * from "+APPOINTMENT_TABLE+" where username = ? and status = 0 and date > ? order by date asc";
	public static final String GET_PAST_APPOINTMENT_BY_USERNAME = "select * from "+APPOINTMENT_TABLE+" where (username = ? and date < ?) or status = 1 order by date desc";
	public static final String GET_PAST_APPOINTMENT_BY_DOCTORUSERNAME = "select * from "+APPOINTMENT_TABLE+" where (doctorusername = ? and date < ?) or status = 1 order by date desc";
	public static final String GET_FUTURE_APPOINTMENT_BY_DOCTORUSERNAME = "select * from "+APPOINTMENT_TABLE+" where doctorusername = ? and status = 0 and date > ? order by date asc";
	public static final String UPDATE_PRESCRIPTION_STATUS = "update "+APPOINTMENT_TABLE+" set prescription=? , status = ? where id = ?";
	public static final String DELETE_APPOINTMENT = "delete from "+APPOINTMENT_TABLE+" where id = ?";
	
	public static String passwordHashing(String password) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA");
		messageDigest.update(password.getBytes());
		byte[] resultByteArray = messageDigest.digest();
		String resultString = new String(resultByteArray);
		return resultString;
	}
}
