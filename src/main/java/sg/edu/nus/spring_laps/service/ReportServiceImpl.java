package sg.edu.nus.spring_laps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.repository.ApplicationRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{
    @Autowired
    private ApplicationRepository applicationRepository;

    public List<Application> getApplicationsByPeriodAndType(LocalDateTime startTime, LocalDateTime endTime, String applicationType) {
        if (applicationType.equals("All")) {
            return applicationRepository.findByStartTimeBetween(startTime, endTime);
        }
        return applicationRepository.findByStartTimeBetweenAndApplicationType(startTime, endTime, applicationType);
    }

    public List<Application> getCompensationClaimsByStaffUserId(String userId) {
        return applicationRepository.findByStaffUserIdAndApplicationType(userId, "Compensation");
    }

    public List<Application> getAllCompensationClaims() {
        return applicationRepository.findByApplicationType("Compensation");
    }
}
