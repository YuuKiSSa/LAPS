package sg.edu.nus.spring_laps.service;

import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationType;
import sg.edu.nus.spring_laps.model.Staff;

import java.util.List;

public interface ApplicationService {
    Application saveApplication(Application application);
    List<ApplicationType> findAllApplicationTypes();
    ApplicationType findApplicationTypeByName(String applicationTypeName);
    Application findApplicationById(Long applicationId);
    List<Application> findApplicationsByStaff(Staff staff);
    ApplicationType findApplicationTypeById(int applicationTypeId);
    List<Application> findMedicalLeaveByStaffAndYear(Staff staff, int year);
    List<Application> findApplicationsByStaffAndApplicationType(Staff staff, ApplicationType type);
    List<Application> findAnnualLeaveByStaffAndYear(Staff staff, int year);
}
