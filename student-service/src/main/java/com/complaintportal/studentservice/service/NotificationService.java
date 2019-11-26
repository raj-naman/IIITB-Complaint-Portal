package com.complaintportal.studentservice.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.complaintportal.studentservice.model.Student;

@Service
public class NotificationService {
	
	private JavaMailSender javaMailSender;
	
	@Autowired
	public NotificationService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	public String sendNotification(Student student) throws MailException{
		SimpleMailMessage mail = new SimpleMailMessage();
		String password = passwordGenerator();
		mail.setTo(student.getEmailId());
		mail.setFrom("naman11396@gmail.com");
		mail.setSubject("Complaint Portal Password");
		mail.setText("Hi, " + student.getRollNo() + " Your password for IIITB Complaint Portal is: " + password + ". "+ "Please Login "
				     + "to the Portal for posing Complaints.");
		
		javaMailSender.send(mail);
		
		return password;
	}
	
	public String passwordGenerator() {
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
        String Small_chars = "abcdefghijklmnopqrstuvwxyz"; 
        String numbers = "0123456789"; 
  
        String values = Capital_chars + Small_chars + numbers; 
        Random rand = new Random(); 
        
        int len  = 6;
        String password = ""; 
  
        for (int i = 0; i < len; i++){ 
            password += values.charAt(rand.nextInt(values.length())); 
  
        } 
        return password;
	}


}
