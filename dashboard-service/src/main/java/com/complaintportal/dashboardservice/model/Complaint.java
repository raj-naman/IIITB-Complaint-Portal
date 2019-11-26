package com.complaintportal.dashboardservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Complaint 
{
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String rollNo;
	private String commiteeId;
	private String compbody;
	private String status;
	private String message;
	private String visibility;
	private String created_at;
	private String last_updated;
	private int level;
	private int upvoteCount;
	
//	@ElementCollection
//    @CollectionTable(name = "complaint_upvoters", joinColumns = @JoinColumn(name = "complaint_id"))
//    @Column(name = "upVoters")
//    private Set<String> upVoters = new HashSet<>();
//	//private HashSet<String> upVoters;
	
	
	public Complaint() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Complaint(int id, String rollNo, String commiteeId, String compbody, String status, String message,
			String visibility, String created_at, String last_updated, int level, int upvoteCount) {
		super();
		this.id = id;
		this.rollNo = rollNo;
		this.commiteeId = commiteeId;
		this.compbody = compbody;
		this.status = status;
		this.message = message;
		this.visibility = visibility;
		this.created_at = created_at;
		this.last_updated = last_updated;
		this.level = level;
		this.upvoteCount = upvoteCount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public String getCommiteeId() {
		return commiteeId;
	}

	public void setCommiteeId(String commiteeId) {
		this.commiteeId = commiteeId;
	}

	public String getCompbody() {
		return compbody;
	}

	public void setCompbody(String compbody) {
		this.compbody = compbody;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}

	public String getLast_updated() {
		return last_updated;
	}

	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getUpvoteCount() {
		return upvoteCount;
	}

	public void setUpvoteCount(int upvoteCount) {
		this.upvoteCount = upvoteCount;
	}
	
	
	
	


}