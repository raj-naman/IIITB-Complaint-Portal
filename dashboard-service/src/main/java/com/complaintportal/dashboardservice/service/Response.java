package com.complaintportal.dashboardservice.service;

import java.util.List;

import com.complaintportal.dashboardservice.model.ComplaintCatalog;

public class Response {
	
	  private String status;
	  private List<ComplaintCatalog> complaints;
	  
	  public Response(){
	    
	  }
	  
	
	public Response(String status, List<ComplaintCatalog> complaints) {
		super();
		this.status = status;
		this.complaints = complaints;
	}


	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<ComplaintCatalog> getComplaints() {
		return complaints;
	}

	public void setComplaints(List<ComplaintCatalog> complaints) {
		this.complaints = complaints;
	}

	  
	  
}
