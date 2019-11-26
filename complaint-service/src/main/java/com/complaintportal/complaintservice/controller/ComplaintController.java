package com.complaintportal.complaintservice.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.complaintportal.complaintservice.dao.ComplaintRepo;
import com.complaintportal.complaintservice.dao.UpvotersRepo;
import com.complaintportal.complaintservice.model.Complaint;
import com.complaintportal.complaintservice.model.Complaint_Upvoters;
import com.complaintportal.complaintservice.model.FetchComplaints;
import com.complaintportal.complaintservice.service.NotificationService;
import com.complaintportal.complaintservice.service.Response;

@RestController
public class ComplaintController {
	
	@Autowired
	ComplaintRepo complaintRepo;
	
	@Autowired
	UpvotersRepo upvotersRepo;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	NotificationService notificationService;
	
	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	Map<Integer , Set<String> > upVoters = new HashMap<>();
	
	@CrossOrigin
	@RequestMapping(value="/addComplaint",method=RequestMethod.POST)
	public ResponseEntity<Response> addComplaint(@RequestParam(value="rollNo",required=false) String rollNo,@RequestParam(value="commiteeId",required=false) String commiteeId ,
			 						@RequestParam(value="visibility",required=false) String visibility, @RequestParam(value="compbody",required=false) String compbody  , HttpSession session) {
		//String rollNo = (String) session.getAttribute("username");
		Complaint complaint  = new Complaint(0, rollNo,  commiteeId, compbody, "Pending", "No message", visibility, sdf.format(new Date(System.currentTimeMillis())), sdf.format(new Date(System.currentTimeMillis())), 1, 0);
		complaintRepo.save(complaint);	
		
		ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8093/commitee/getEmailId?commiteeId="+commiteeId, String.class);
		String emailId = entity.getBody();
		notificationService.sendEmailToCommitee(emailId, rollNo);
		
		Response response = new Response("Pass", rollNo);
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);
		
	}
	
	@RequestMapping("/studentComplaints") 
	public FetchComplaints getStudentComplaints(@RequestParam(value="rollNo",required=false) String rollNo) {
		List<Complaint> complaints = (List<Complaint>)complaintRepo.findByRollNo(rollNo);
		FetchComplaints studentComplaints = new FetchComplaints(rollNo , complaints);
		return studentComplaints;
	}
	
	@RequestMapping("/commiteeComplaints") 
	public FetchComplaints getCommiteeComplaints(@RequestParam(value="commiteeId",required=false) String commiteeId) {
		List<Complaint> complaints = (List<Complaint>)complaintRepo.findByCommiteeId(commiteeId);
		FetchComplaints commiteeComplaints = new FetchComplaints(commiteeId , complaints);
		return commiteeComplaints;
	}
	
	@RequestMapping("/publicComplaints") 
	public FetchComplaints getPublicComplaints() {
		List<Complaint> complaints = (List<Complaint>)complaintRepo.findByPublicVisibility();
		FetchComplaints publicComplaints = new FetchComplaints("Public" , complaints);
		return publicComplaints;
	}
	
	@RequestMapping("/allComplaints") 
	public FetchComplaints getAllComplaints() {
		List<Complaint> complaints = (List<Complaint>)complaintRepo.findAll();
		FetchComplaints allComplaints = new FetchComplaints("All" , complaints);
		return allComplaints;
	}
	
	@GetMapping("/complaint/{id}")
	public Complaint getComplaintDetails(@PathVariable("id") int id) {

		Complaint complaint = complaintRepo.findById(id).orElse(null);
		return complaint;
	}
	
	@SuppressWarnings("null")
	@CrossOrigin
	@RequestMapping(value="/complaint/update",method=RequestMethod.POST)
	public ResponseEntity<Response> updateComplaint(@RequestParam(value="id",required=false) int id , @RequestParam(value="status",required=false) String status ,
			@RequestParam(value="message",required=false) String message) {

		complaintRepo.updateComplaint(status, message, sdf.format(new Date(System.currentTimeMillis())), id);
		Complaint complaint = complaintRepo.findById(id).orElse(null);
		Response response = null;
		if(complaint == null) {
			response = new Response("Fail", complaint.getRollNo());
		}
		else {
			ResponseEntity<String> entity = restTemplate.getForEntity("http://localhost:8092/student/getEmailId?rollNo="+complaint.getRollNo(), String.class);
			String emailId = entity.getBody();
			notificationService.sendEmailToStudent(emailId);
			response = new Response("Pass", complaint.getRollNo());
		}

		
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
	
	@CrossOrigin
	@RequestMapping(value="/complaint/upvote",method=RequestMethod.POST)
	public ResponseEntity<Response> upvoteComplaint(@RequestParam(value="id",required=false) int id ,  @RequestParam(value="rollNo",required=false) String rollNo ) {
		Response response = null;
		if(upVoters.containsKey(id)) {
			Set<String> voters = upVoters.get(id);
			if(voters.contains(rollNo)) {
				response = new Response("Fail", rollNo);
			}
			else {
				voters.add(rollNo);
				upVoters.put(id, voters);
				complaintRepo.increaseUpvoteCount(id);
				Complaint_Upvoters complaint_upvoters = new Complaint_Upvoters(0, id, rollNo);
				upvotersRepo.save(complaint_upvoters);
				response = new Response("Pass", rollNo);
			}
		}
		else {
			Set<String> voters = new HashSet<>();
			voters.add(rollNo);
			upVoters.put(id, voters);
			complaintRepo.increaseUpvoteCount(id);
			Complaint_Upvoters complaint_upvoters = new Complaint_Upvoters(0, id, rollNo);
			upvotersRepo.save(complaint_upvoters);
			response = new Response("Pass", rollNo);
		}	
		
		return new ResponseEntity<Response>(response, HttpStatus.OK);

	}
}
