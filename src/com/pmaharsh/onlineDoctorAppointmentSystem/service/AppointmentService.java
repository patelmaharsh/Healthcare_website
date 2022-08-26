package com.pmaharsh.onlineDoctorAppointmentSystem.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.pmaharsh.onlineDoctorAppointmentSystem.dao.AppointmentDao;
import com.pmaharsh.onlineDoctorAppointmentSystem.model.Appointment;
import com.pmaharsh.onlineDoctorAppointmentSystem.model.Doctor;

public class AppointmentService {
	public int bookAppointment(String datetime, String username, String doctorUsername) {
		AppointmentDao dao = new AppointmentDao();
		Appointment appointment = new Appointment();
		AuthenticationService authService = new AuthenticationService();
		Doctor d = authService.getDoctorByUsername(doctorUsername);
		String newDate = datetime.substring(0, 10)+" "+datetime.substring(11)+":00";
		appointment.setUsername(username);
		appointment.setDoctorUsername(doctorUsername);
		appointment.setDate(datetime);
		appointment.setCategory(d.getCategory());
		appointment.setId(dao.getMaxId()+1);
		appointment.setStatus(false);
		return dao.saveAppointment(appointment);
		
	}
	public ArrayList<Appointment> getAppointmentsByUsername(String username, boolean isPast){
		AppointmentDao dao = new AppointmentDao();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		ArrayList<Appointment> appointments = dao.getAppointmentByUsername(username, formatter.format(date),isPast);
 		return appointments;
	}
	public ArrayList<Appointment> getAppointmentsByDoctorUsername(String username, boolean isPast){
		AppointmentDao dao = new AppointmentDao();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		ArrayList<Appointment> appointments = dao.getAppointmentByDoctorUsername(username, formatter.format(date),isPast);
		return appointments;
	}
	public int updatePrescriptionAndStatus(String prescription, boolean status, int id) {
		AppointmentDao dao = new AppointmentDao();
		int result = dao.updatePrescriptionAndStatus(prescription, status, id);
		return result;
	}
	public int deleteAppointment(int appointmentId) {
		AppointmentDao dao = new AppointmentDao();
		int status = dao.deleteAppointment(appointmentId);
		return status;
	}
}
