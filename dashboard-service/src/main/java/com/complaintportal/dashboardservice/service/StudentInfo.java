package com.complaintportal.dashboardservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.complaintportal.dashboardservice.model.Complaint;
import com.complaintportal.dashboardservice.model.ComplaintCatalog;
import com.complaintportal.dashboardservice.model.Student;

@Service
public class StudentInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	//@HystrixCommand(fallbackMethod = "getFallbackComplaintCatalog")
	public ComplaintCatalog getStudentInfo(Complaint complaint) {
		Student student = restTemplate.getForObject("http://localhost:8092/student/?rollNo="+complaint.getRollNo(), Student.class);
		return new ComplaintCatalog(complaint.getId(),student.getRollNo(),student.getEmailId(),student.getName(),
				student.getHostel(),student.getRoomNo(),student.getMobile(),complaint.getCommiteeId(),
				complaint.getCompbody(),complaint.getStatus(),complaint.getMessage(),
				complaint.getVisibility(),complaint.getCreated_at(),complaint.getLast_updated(),complaint.getUpvoteCount());
	}
	
//	public String getFallbackComplaintCatalog(Complaint complaint) {
//		return "Not found";
//	}

}
