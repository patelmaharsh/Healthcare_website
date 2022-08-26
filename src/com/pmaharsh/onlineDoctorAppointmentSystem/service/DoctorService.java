package com.pmaharsh.onlineDoctorAppointmentSystem.service;

import java.util.ArrayList;

import com.pmaharsh.onlineDoctorAppointmentSystem.dao.DoctorDao;
import com.pmaharsh.onlineDoctorAppointmentSystem.model.Doctor;

public class DoctorService {
	public ArrayList<Doctor> getDoctorByCityCategory(String city, String category){
		DoctorDao dao = new DoctorDao();
		ArrayList<Doctor> listDoctor = dao.getDoctorByCityCategory(city, category);
		return listDoctor;
	}
	public ArrayList<String> getCategories(){
		DoctorDao dao = new DoctorDao();
		ArrayList<String> listCategory = dao.getCategory();
		return listCategory;
	}
}
