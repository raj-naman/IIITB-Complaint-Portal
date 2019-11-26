package com.complaintportal.studentservice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.complaintportal.studentservice.dao.StudentRepo;
import com.complaintportal.studentservice.model.Student;
import com.complaintportal.studentservice.service.NotificationService;
import com.complaintportal.studentservice.service.Response;

@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentRepo studentRepo;
	
	@Autowired
	NotificationService notificationService;
	
	@CrossOrigin
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public ResponseEntity<Response> studentLogin(@RequestParam(value="rollNo",required = false) String rollNo , @RequestParam(value="password",required = false) String password, HttpSession session) 
	{
		
		Response response;
		if((String)session.getAttribute("username") == null) {
			if(studentRepo.findByRollNoAndPassword(rollNo, password) != null && !password.equals("")) {
				session.setAttribute("username", rollNo);
				System.out.println(session.getAttribute("username"));
				response = new Response("Pass", (String) session.getAttribute("username"));
				
			}
			else {
				response = new Response("Fail", rollNo);
				
			}
		}
		else {
			response = new Response("Fail1", rollNo);
		}
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/addStudent",method=RequestMethod.POST)
	public ResponseEntity<Response> addStudent(@RequestParam(value="rollNo",required = false) String rollNo ,
									  @RequestParam(value="roomNo",required = false) String roomNo, @RequestParam(value="hostel",required = false) String hostel) {
		Response response;
		if(studentRepo.findByRollNoAndRoomNo(rollNo,roomNo) == null) {
			Student student = new Student(0, rollNo,  "", "", hostel, roomNo, "", "");
			studentRepo.save(student);	
			response = new Response("Pass", rollNo);
		}
		else if(studentRepo.findByRollNo(rollNo) != null) {
			Student student = studentRepo.findByRollNo(rollNo);
			String room = student.getRoomNo();
			if(!room.equals(roomNo)) {
				studentRepo.updateRoom(roomNo , rollNo);
				response = new Response("Pass1" , rollNo);
			}
			else {
				response = new Response("Fail" , rollNo);
			}
		}
		else {
				response = new Response("Fail" , rollNo);
			
		}
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		
	}
	
	@GetMapping("/")
	public Student getStudentDetails(@RequestParam(value="rollNo",required = false) String rollNo) {

		Student student = studentRepo.findByRollNo(rollNo);
		if(student==null)student=new Student();
		return student;
	}
	
	@GetMapping("/getEmailId")
	public String getStudentEmail(@RequestParam(value="rollNo",required = false) String rollNo) {

		Student student = studentRepo.findByRollNo(rollNo);
		String emailId = student.getEmailId();
		return emailId;
	}
	
	@CrossOrigin
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public ResponseEntity<Response> registerStudent(Student student) {
		String emailId = student.getEmailId();
		Response response = null;
		if(emailId.endsWith("@iiitb.org")) {
			if(studentRepo.findByRollNoAndRoomNo(student.getRollNo(), student.getRoomNo()) != null) {
				try{
					String password = notificationService.sendNotification(student);
					studentRepo.updateStudent(password, student.getEmailId() , student.getName() ,student.getMobile(), student.getRollNo());
					response = new Response("Pass" , student.getRollNo());
				}
				catch(MailException e) {
					System.out.println(e);
				}
			}
			else{
				response = new Response("Fail1" , student.getRollNo());
			}
		}
		else{
			response = new Response("Fail2" , student.getRollNo());
		}
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/changepassword",method=RequestMethod.POST)
	public ResponseEntity<Response> changePassword(@RequestParam(value="rollNo",required=false) String rollNo , @RequestParam(value="oldpass",required=false) String oldpass,
								 @RequestParam(value="newpass",required=false) String newpass) {
		
		Response response;
		Student student = studentRepo.findByRollNo(rollNo);
		if(student != null) {
			if(student.getPassword().equals(oldpass)) {
				studentRepo.updatePassword(newpass, rollNo);
				response = new Response("Pass" , rollNo);
			}
			else {
				response = new Response("Fail1" , rollNo);
			}
		}
		else {
			response = new Response("Fail12" , rollNo);
		}
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value="/forgotpassword",method=RequestMethod.POST)
	public ResponseEntity<Response> forgotPassword(@RequestParam(value="rollNo",required=false) String rollNo) {
		Response response = null;
		if(studentRepo.findByRollNo(rollNo) != null) {
			Student student = studentRepo.findByRollNo(rollNo);
			try{
				String password = notificationService.sendNotification(student);
				studentRepo.updatePassword(password, rollNo);
				response = new Response("Pass" , rollNo);
			}
			catch(MailException e) {
				System.out.println(e);
			}
		}
		else{
			response = new Response("Fail" , rollNo);
		}
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
	}
	
}
