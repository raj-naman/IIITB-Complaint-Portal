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
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	CommiteeRepo commiteeRepo;
	
	@CrossOrigin
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	public ResponseEntity<CommiteeResponse> adminLogin(@RequestParam(value="commiteeId",required=false) String commiteeId , @RequestParam(value="password",required=false) String password) 
	{
		CommiteeResponse commiteeResponse;
		if(commiteeId.equals("admin") && commiteeRepo.findByCommiteeIdAndPassword(commiteeId, password) != null) {
			commiteeResponse = new CommiteeResponse("Pass", commiteeId);
		}
		else {
			commiteeResponse = new CommiteeResponse("Fail", commiteeId);
		}
		return new ResponseEntity<CommiteeResponse>(commiteeResponse, HttpStatus.OK);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/addCommitee",method=RequestMethod.POST)
	public ResponseEntity<CommiteeResponse> addCommitee(Commitee commitee) 
	{
		CommiteeResponse commiteeResponse;
		if(commiteeRepo.findByCommiteeId(commitee.getCommiteeId()) == null) {
			//Commitee commitee = new Commitee(0, commiteeId, emailId, password);
			commiteeRepo.save(commitee);
			commiteeResponse = new CommiteeResponse("Pass", commitee.getCommiteeId());
		}
		else {
			commiteeResponse = new CommiteeResponse("Fail", commitee.getCommiteeId());
		}
		return new ResponseEntity<CommiteeResponse>(commiteeResponse, HttpStatus.OK);
	}
	
	
}
