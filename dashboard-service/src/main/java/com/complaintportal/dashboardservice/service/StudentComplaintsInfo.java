package com.complaintportal.dashboardservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import com.complaintportal.dashboardservice.model.FetchComplaints;

@Service
public class StudentComplaintsInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	//@HystrixCommand(fallbackMethod = "getFallbackStudentComplaints")
	public FetchComplaints getStudentComplaintsInfo(@PathVariable("rollNo") String rollNo) {
		return restTemplate.getForObject("http://localhost:8094/studentComplaints?rollNo=" + rollNo, FetchComplaints.class);
	}
	
//	public GetComplaints getFallbackStudentComplaints(@PathVariable("userId") String userId) {
//		UserRating userRating = new UserRating();
//		userRating.setUserId(userId);
//		userRating.setRatings(Arrays.asList(
//			new Rating("0" , 0)
//		));
//		return userRating;
//	}

}
