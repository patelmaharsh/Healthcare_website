package com.pmaharsh.onlineDoctorAppointmentSystem.model;

public class Appointment {
	private int id,timeSlot;
	private String username,doctorUsername,prescription, category;
	private String date;
	private boolean status;
	public Appointment(int id, int timeSlot, String username, String doctorUsername, String prescription,
			String category, String date, boolean status) {
		super();
		this.id = id;
		this.timeSlot = timeSlot;
		this.username = username;
		this.doctorUsername = doctorUsername;
		this.prescription = prescription;
		this.category = category;
		this.date = date;
		this.status = status;
	}
	public Appointment() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getTimeSlot() {
		return timeSlot;
	}
	public void setTimeSlot(int timeSlot) {
		this.timeSlot = timeSlot;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getDoctorUsername() {
		return doctorUsername;
	}
	public void setDoctorUsername(String doctorUsername) {
		this.doctorUsername = doctorUsername;
	}
	public String getPrescription() {
		return prescription;
	}
	public void setPrescription(String prescription) {
		this.prescription = prescription;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Appointment [id=" + id + ", timeSlot=" + timeSlot + ", username=" + username + ", doctorUsername="
				+ doctorUsername + ", prescription=" + prescription + ", category=" + category + ", date=" + date
				+ ", status=" + status + "]";
	}
	
	
}
