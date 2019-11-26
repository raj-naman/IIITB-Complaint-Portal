package com.complaintportal.studentservice.service;

public class Response {
	
	  private String status;
	  private String rollNo;
	  
	  public Response(){
	    
	  }

	public Response(String status, String rollNo) {
		super();
		this.status = status;
		this.rollNo = rollNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	  
	  
}
