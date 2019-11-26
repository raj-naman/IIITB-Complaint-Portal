package com.complaintportal.complaintservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Complaint_Upvoters {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int complaintId;
	private String rollNo;
	
	public Complaint_Upvoters(int id, int complaintId, String rollNo) {
		super();
		this.id = id;
		this.complaintId = complaintId;
		this.rollNo = rollNo;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getComplaintId() {
		return complaintId;
	}
	public void setComplaintId(int complaintId) {
		this.complaintId = complaintId;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	
	

}
