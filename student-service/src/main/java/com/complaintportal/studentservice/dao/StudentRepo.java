package com.complaintportal.studentservice.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.complaintportal.studentservice.model.Student;

@Repository
public interface StudentRepo extends JpaRepository<Student, Integer> {
	
	public Student findByRollNo(String rollNo); 
	
	public Student findByRollNoAndPassword(String rollNo , String password); 
	
	public Student findByRollNoAndRoomNo(String rollNo , String roomNo); 
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE student s set s.password = ?1 , s.email_id = ?2 , s.name = ?3, s.mobile = ?4 WHERE s.roll_no=?5 ", nativeQuery=true)
	void updateStudent(String password , String emailId , String name , String mobile, String rollNo);
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE student s set s.password = ?1 WHERE s.roll_no=?2 ", nativeQuery=true)
	void updatePassword(String password , String rollNo);

	@Modifying(clearAutomatically = true)
	@Transactional
	@Query(value = "UPDATE student s set s.room_no = ?1 WHERE s.roll_no=?2 ", nativeQuery=true)
	void updateRoom(String roomNo , String rollNo);

}

