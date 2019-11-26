package com.complaintportal.complaintservice.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.complaintportal.complaintservice.model.Complaint;

@Repository
public interface ComplaintRepo extends JpaRepository<Complaint, Integer> {
	//List<Complaint> findByCtype(String ctype);

	List<Complaint> findByRollNo(String rollNo);
	
	List<Complaint> findByCommiteeId(String commiteeId);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE complaint t set t.status = ?1 , t.message = ?2, t.last_updated = ?3 WHERE t.id=?4 ", nativeQuery = true)
	void updateComplaint(String status, String message, String newTimestamp, int id);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE complaint t set t.upvote_count = t.upvote_count + 1 WHERE t.id=?1 ", nativeQuery = true)
	void increaseUpvoteCount(int id);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "SELECT * from complaint WHERE last_updated < ?1 AND status = ('Pending' OR 'Accepted') AND level = 1 ", nativeQuery = true)
	List<Complaint> findOlderThanTwoDaysComplaint(String olderTimestamp);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "SELECT * from complaint WHERE visibility = 'Public' ", nativeQuery = true)
	List<Complaint> findByPublicVisibility();
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE complaint t set t.message = ?1, t.last_updated = ?2, t.level = ?3 WHERE t.id=?4 ", nativeQuery = true)
	void updatePendingComplaint(String message, String newTimestamp, int newLevel, int id);
	
	
	
//	@Modifying(clearAutomatically = true)
//	@Transactional
//	@Query(value = "INSERT INTO complaint_upvoters (complaint_id , upVoters) VALUES (?1 , ?2)", nativeQuery = true)
//	void addUpvoter(int id , String rollNo);

}
