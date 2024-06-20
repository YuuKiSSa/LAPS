package sg.edu.nus.spring_laps.service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.repository.ApplicationRepository;
@Service
public class ReportServiceImpl implements ReportService{
	 @Autowired
	 private ApplicationRepository applicationRepository;
	 
	 public List<Application> getApplicationsByPeriodAndType(LocalDateTime startTime, LocalDateTime endTime, String type) {
	        if (type.equals("all")) {
	            return applicationRepository.findByStartTimeBetween(startTime, endTime);
	        }
	        return applicationRepository.findByStartTimeBetweenAndApplicationType_Type(startTime, endTime, type);
	    }

	    public List<Application> getCompensationClaimsByStaffUserId(String userId) {
	        return applicationRepository.findByStaffUserIdAndApplicationType_Type(userId, "compensation");
	    }

	    public List<Application> getAllCompensationClaims() {
	        return applicationRepository.findByApplicationType_Type("compensation");
	    }
}
