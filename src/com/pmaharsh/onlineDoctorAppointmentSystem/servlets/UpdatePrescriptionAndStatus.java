package com.pmaharsh.onlineDoctorAppointmentSystem.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pmaharsh.onlineDoctorAppointmentSystem.dao.AppointmentDao;
import com.pmaharsh.onlineDoctorAppointmentSystem.service.AppointmentService;

@WebServlet("/updatePrescriptionAndStatus")
public class UpdatePrescriptionAndStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String prescription = req.getParameter("prescription");
		boolean status = (req.getParameter("isCompleted")!=null && req.getParameter("isCompleted").compareTo("on")==0)?true:false;
		int appointmentId = Integer.parseInt(req.getParameter("id"));
		AppointmentService service = new AppointmentService();
		int result = service.updatePrescriptionAndStatus(prescription, status, appointmentId);
		if(result==0) System.out.println("appointment not updated");
		else System.out.println("appointment updated");
		res.sendRedirect("doctorhome.jsp");
	}

}
