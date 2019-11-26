package com.complaintportal.complaintservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.complaintportal.complaintservice.model.Complaint;

@Service
public class NotificationService {
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public void sendNotification(Complaint complaint , String emailId) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailId);
		mail.setFrom("admin@iiitb.ac.in");
		mail.setSubject("Complaint Portal Password");
		mail.setText("Hi, Complaint by " + complaint.getRollNo() + " is not Resolved by respective commitee. Please look the complaint and resolve it.  " + "\n" + "Complaint is: " + complaint.getCompbody());
		
		javaMailSender.send(mail);
	}
	
	public void sendEmailToStudent(String emailId) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailId);
		mail.setFrom("admin@iiitb.ac.in");
		mail.setSubject("Status is updated");
		mail.setText("Hi, Status of your Complaint is updated. Please check the portal.");
		
		javaMailSender.send(mail);
	}
	
	public void sendEmailToCommitee(String emailId, String rollNo) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(emailId);
		mail.setFrom("admin@iiitb.ac.in");
		mail.setSubject("New Complaint Added");
		mail.setText("Hi, New Complaint is added by student with roll no. " + rollNo + ". Please check the portal.");
		
		javaMailSender.send(mail);
	}
	
	


}
