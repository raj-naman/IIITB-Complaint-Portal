package com.complaintportal.commiteeservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.complaintportal.commiteeservice.model.Commitee;

@Repository
public interface CommiteeRepo extends JpaRepository<Commitee, Integer> {
	
	public Commitee findByCommiteeIdAndPassword(String commiteeId, String password);

	public Commitee findByCommiteeId(String commiteeId); 
	
//	@Modifying(clearAutomatically = true)
//	@Transactional
//	@Query(value = "UPDATE commitee s set s.password = ?1 WHERE s.commiteeId=?2 ", nativeQuery=true)
//	void updatePassword(String password , String commiteeId);

}