package com.complaintportal.commiteeservice.service;

public class CommiteeResponse {
	
	private String status;
	private String commiteeId;
	public CommiteeResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public CommiteeResponse(String status, String commiteeId) {
		super();
		this.status = status;
		this.commiteeId = commiteeId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCommiteeId() {
		return commiteeId;
	}
	public void setCommiteeId(String commiteeId) {
		this.commiteeId = commiteeId;
	}
	
	

}
