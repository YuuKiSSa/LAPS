package sg.edu.nus.spring_laps.service;

import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.Staff;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    List<Application> getApplicationsByPeriodAndType(LocalDateTime startTime, LocalDateTime endTime, String applicationType);
    List<Application> getCompensationClaimsByStaffUserId(String userId);
    List<Application> getAllCompensationClaims();
}
