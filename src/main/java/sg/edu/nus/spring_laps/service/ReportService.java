package sg.edu.nus.spring_laps.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.Staff;

public interface ReportService {
	public List<Application> getApplicationsByPeriodAndType(Date startTime, Date endTime, String type);
	public List<Application> getCompensationClaimsByStaffUserId(String userId);
	public List<Application> getAllCompensationClaims(); 
}
