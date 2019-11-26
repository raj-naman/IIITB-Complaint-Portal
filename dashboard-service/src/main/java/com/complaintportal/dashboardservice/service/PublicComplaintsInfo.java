package com.complaintportal.dashboardservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.complaintportal.dashboardservice.model.FetchComplaints;

@Service
public class PublicComplaintsInfo {
	
	@Autowired
	private RestTemplate restTemplate;
	
	//@HystrixCommand(fallbackMethod = "getFallbackPublicComplaints")
	public FetchComplaints getPublicComplaintsInfo() {
		return restTemplate.getForObject("http://localhost:8094/publicComplaints", FetchComplaints.class);
	}
	
//	public GetComplaints getFallbackPublicComplaints(@PathVariable("userId") String userId) {
//		UserRating userRating = new UserRating();
//		userRating.setUserId(userId);
//		userRating.setRatings(Arrays.asList(
//			new Rating("0" , 0)
//		));
//		return userRating;
//	}


}
