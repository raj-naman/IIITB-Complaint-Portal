package com.complaintportal.complaintservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.complaintportal.complaintservice.model.Complaint_Upvoters;

@Repository
public interface UpvotersRepo extends JpaRepository<Complaint_Upvoters, Integer> {

}
