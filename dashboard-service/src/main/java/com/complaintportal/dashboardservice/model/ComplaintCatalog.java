package com.complaintportal.dashboardservice.model;

public class ComplaintCatalog {
	
	private int id;
	private String rollNo;
	private String emailId;
	private String name;
	private String hostel;
	private String roomNo;
	private String mobile;
	private String commiteeId;
	private String compbody;
	private String status;
	private String message;
	private String visibility;
	private String created_at;
	private String last_updated;
	private int upvoteCount;
	
	
	public ComplaintCatalog() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ComplaintCatalog(int id, String rollNo, String emailId, String name, String hostel, String roomNo,
			String mobile, String commiteeId, String compbody, String status, String message, String visibility,
			String created_at, String last_updated, int upvoteCount) {
		super();
		this.id = id;
		this.rollNo = rollNo;
		this.emailId = emailId;
		this.name = name;
		this.hostel = hostel;
		this.roomNo = roomNo;
		this.mobile = mobile;
		this.commiteeId = commiteeId;
		this.compbody = compbody;
		this.status = status;
		this.message = message;
		this.visibility = visibility;
		this.created_at = created_at;
		this.last_updated = last_updated;
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
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHostel() {
		return hostel;
	}
	public void setHostel(String hostel) {
		this.hostel = hostel;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
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
	public int getUpvoteCount() {
		return upvoteCount;
	}
	public void setUpvoteCount(int upvoteCount) {
		this.upvoteCount = upvoteCount;
	}
	
	
	
	

}
