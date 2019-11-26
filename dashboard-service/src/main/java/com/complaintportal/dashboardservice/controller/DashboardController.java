package com.complaintportal.dashboardservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.complaintportal.dashboardservice.model.ComplaintCatalog;
import com.complaintportal.dashboardservice.model.FetchComplaints;
import com.complaintportal.dashboardservice.service.AllComplaintsInfo;
import com.complaintportal.dashboardservice.service.CommiteeComplaintsInfo;
import com.complaintportal.dashboardservice.service.PublicComplaintsInfo;
import com.complaintportal.dashboardservice.service.StudentComplaintsInfo;
import com.complaintportal.dashboardservice.service.StudentInfo;

@RestController
public class DashboardController {
	
	@Autowired
	StudentInfo studentInfo;
	
	@Autowired
	StudentComplaintsInfo studentComplaintsInfo;
	
	@Autowired
	CommiteeComplaintsInfo commiteeComplaintsInfo;
	
	@Autowired
	PublicComplaintsInfo publicComplaintsInfo; 
	
	@Autowired
	AllComplaintsInfo allComplaintsInfo; 
	
	@RequestMapping("/dashboard") 
	public List<ComplaintCatalog> getMainDashboard() {
		
		FetchComplaints fetchComplaints = publicComplaintsInfo.getPublicComplaintsInfo();
		//System.out.println(fetchComplaints.getComplaints());
		return fetchComplaints.getComplaints().stream().map(complaint -> 
		
			studentInfo.getStudentInfo(complaint))
			.collect(Collectors.toList());
		
		
	}
	
	@RequestMapping("/dashboard/student") 
	public List<ComplaintCatalog> getStudentDashboard(@RequestParam(value="rollNo",required=false) String rollNo) {
		
		FetchComplaints fetchComplaints = studentComplaintsInfo.getStudentComplaintsInfo(rollNo);
		System.out.println(fetchComplaints.getComplaints());
		return fetchComplaints.getComplaints().stream().map(complaint -> 
		
		studentInfo.getStudentInfo(complaint))
		.collect(Collectors.toList());
		
		
	}
	
	@RequestMapping("/dashboard/commitee") 
	public List<ComplaintCatalog> getCommiteeDashboard(@RequestParam(value="commiteeId",required=false) String commiteeId) {
		
		FetchComplaints fetchComplaints = commiteeComplaintsInfo.getCommiteeComplaintsInfo(commiteeId);
		System.out.println("Data size :: "+fetchComplaints.getComplaints().size());
		return fetchComplaints.getComplaints().stream().map(complaint -> 
		studentInfo.getStudentInfo(complaint))
		.collect(Collectors.toList());
		
//		Response response = new Response("Pass" , commiteeComplaints);
//		
//		return ResponseEntity.ok()
//		        .header("Access-Control-Allow-Origin", "*")
//		        .body(response);
		
		
	}
	
	@RequestMapping("/dashboard/admin") 
	public List<ComplaintCatalog> getAdminDashboard() {
		
		FetchComplaints fetchComplaints = allComplaintsInfo.getAllComplaintsInfo();
		//System.out.println(fetchComplaints.getComplaints());
		return fetchComplaints.getComplaints().stream().map(complaint -> 
		
			studentInfo.getStudentInfo(complaint))
			.collect(Collectors.toList());
		
		
	}

}
