package com.complaintportal.commiteeservice.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Commitee {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private String commiteeId;
	private String emailId;
	private String password;
	
	
	
	public Commitee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Commitee(int id, String commiteeId, String emailId, String password) {
		super();
		this.id = id;
		this.commiteeId = commiteeId;
		this.emailId = emailId;
		this.password = password;
	} 
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCommiteeId() {
		return commiteeId;
	}
	public void setCommiteeId(String commiteeId) {
		this.commiteeId = commiteeId;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
