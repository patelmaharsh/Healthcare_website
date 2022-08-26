package com.pmaharsh.onlineDoctorAppointmentSystem.service;

import com.pmaharsh.onlineDoctorAppointmentSystem.dao.DoctorDao;
import com.pmaharsh.onlineDoctorAppointmentSystem.dao.UserDao;
import com.pmaharsh.onlineDoctorAppointmentSystem.model.Doctor;
import com.pmaharsh.onlineDoctorAppointmentSystem.model.User;

public class AuthenticationService {
	
	public User isUsernamePasswordValid(String username,String password) {
		System.out.println("Inside AuthenticationService -> isUsernamePasswordValid");
		UserDao dao = new UserDao();
		User u = dao.getUserByUsername(username);
		return u;
	}

	public void saveUser(User u) {
		System.out.println("Inside AuthenticationService -> saveUser");
		if(isUserDetailsValid(u)) {
			UserDao dao = new UserDao();
			int status = dao.addUser(u);
			if(status>0) System.out.println("user added successfully");
		} else {
			System.out.println("Something missing in user details");
		}
	}

	private boolean isUserDetailsValid(User u) {
		if(u.getName().trim().length()<3) {
			System.out.println("invalid name");
		} else if(u.getUsername().trim().length()<3) {
			System.out.println("invalid username");
		} else if(u.getPassword().trim().length()<8) {
			System.out.println("invalid password");
		} else if(u.getAge() != null && u.getAge()<1) {
			System.out.println("invalid age");
		} else if(u.getEmail().trim().length()<5) {
			System.out.println("invalid email");
		}
		return true;
	}
	public User getUserByUsername(String username) {
		UserDao dao = new UserDao();
		User u = dao.getUserByUsername(username);
		if(u==null) System.out.println("user not found with username "+ username);
		return u;
	}
	public Doctor getDoctorByUsername(String username) {
		DoctorDao dao = new DoctorDao();
		Doctor d = dao.getDoctorByUsername(username);
		if(d==null) System.out.println("doctor not found with username "+ username);
		return d;
	}
	
}
