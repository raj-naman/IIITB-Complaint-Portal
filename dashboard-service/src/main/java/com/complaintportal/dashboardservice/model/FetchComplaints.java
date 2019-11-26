package com.complaintportal.dashboardservice.model;

import java.util.List;

public class FetchComplaints {
	
	private String id;
    private List<Complaint> complaints;
    
    
	public FetchComplaints() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FetchComplaints(String id, List<Complaint> complaints) {
		super();
		this.id = id;
		this.complaints = complaints;
	}
	
	public String getid() {
		return id;
	}
	public void setid(String id) {
		this.id = id;
	}
	public List<Complaint> getComplaints() {
		return complaints;
	}
	public void setComplaints(List<Complaint> complaints) {
		this.complaints = complaints;
	}
    
    
}
