package sg.edu.nus.spring_laps.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.repository.ApplicationRepository;
@Service
public class ReportServiceImpl implements ReportService{
	 @Autowired
	 private ApplicationRepository applicationRepository;
	 
	 public List<Application> getApplicationsByPeriodAndType(Date startTime, Date endTime, String applicationtype) {
	        if (applicationtype.equals("all")) {
	            return applicationRepository.findByStartTimeBetween(startTime, endTime);
	        }
	        return applicationRepository.findByStartTimeBetweenAndApplicationtype(startTime, endTime, applicationtype);
	    }

	    public List<Application> getCompensationClaimsByStaff(Staff staff) {
	        return applicationRepository.findByStaffAndApplicationtype(staff, "compensation");
	    }

	    public List<Application> getAllCompensationClaims() {
	        return applicationRepository.findByApplicationtype("compensation");
	    }
}
