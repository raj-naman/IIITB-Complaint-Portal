package com.complaintportal.commiteeservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.complaintportal.commiteeservice.dao.CommiteeRepo;
import com.complaintportal.commiteeservice.model.Commitee;
import com.complaintportal.commiteeservice.service.CommiteeResponse;

@RestController
@RequestMapping("/commitee")
public class CommiteeController {
	
	@Autowired
	CommiteeRepo commiteeRepo;
	
	@CrossOrigin
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public ResponseEntity<CommiteeResponse> commiteeLogin(@RequestParam(value="commiteeId",required = false) String commiteeId , @RequestParam(value="password",required = false) String password) 
	{
		CommiteeResponse commiteeResponse;
		if(commiteeRepo.findByCommiteeIdAndPassword(commiteeId, password) != null) {
			commiteeResponse = new CommiteeResponse("Pass", commiteeId);
			
		}
		else {
			commiteeResponse = new CommiteeResponse("Fail", commiteeId);
		}
		return new ResponseEntity<CommiteeResponse>(commiteeResponse, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/getEmailId")
	public String commiteeLogin(@RequestParam(value="commiteeId",required = false) String commiteeId) 
	{
		if(commiteeRepo.findByCommiteeId(commiteeId) != null) {
			Commitee commitee = commiteeRepo.findByCommiteeId(commiteeId);
			return commitee.getEmailId();
			
		}
		else {
			return null;
		}
	}
	
	
}
