package com.complaintportal.complaintservice.scheduler;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.complaintportal.complaintservice.dao.ComplaintRepo;
import com.complaintportal.complaintservice.model.Complaint;
import com.complaintportal.complaintservice.service.NotificationService;

@Component
public class ScheduleJobs {
	
	@Autowired
	ComplaintRepo complaintRepo;
	
	@Autowired
	NotificationService notificationService;
	
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
	
	@Scheduled(cron = "0/30 * * * * *")
    public void cronJobSch() {
		String olderTimestamp = sdf.format(new Date(System.currentTimeMillis() - (long)172800000));
		List<Complaint> olderThanTwoDaysComplaint = complaintRepo.findOlderThanTwoDaysComplaint(olderTimestamp);
        //System.out.println(timestamp1);
		
		String message = "Your Complaint Escalated to higher authority";
		for(Complaint complaint : olderThanTwoDaysComplaint) {
			if(complaint.getCommiteeId().equals("Internet")) {
				notificationService.sendNotification(complaint, "datacente@iiitb.ac.in");
			}
			else {
				notificationService.sendNotification(complaint, "registra@iiitb.ac.in");
			}
			complaintRepo.updatePendingComplaint(message, sdf.format(new Date(System.currentTimeMillis())), 2, complaint.getId());
		}
    }

}
