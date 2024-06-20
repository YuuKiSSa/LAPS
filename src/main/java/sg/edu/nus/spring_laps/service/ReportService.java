package sg.edu.nus.spring_laps.service;

import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.Staff;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

public interface ReportService {
    public List<Application> getApplicationsByPeriodAndType(LocalDateTime startTime, LocalDateTime endTime, String applicationType);
    public List<Application> getCompensationClaimsByStaff(Staff staff);
    public List<Application> getAllCompensationClaims();
}
