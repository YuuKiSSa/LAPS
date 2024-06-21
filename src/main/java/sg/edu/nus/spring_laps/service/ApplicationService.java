package sg.edu.nus.spring_laps.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

	Page<Application> findAllByStaff(Staff staff, Pageable pageable);

	ApplicationType findApplicationTypeById(int applicationTypeId);

	List<Application> findMedicalLeaveByStaffAndYear(Staff staff, int year);

	List<Application> findApplicationsByStaffAndApplicationType(Staff staff, ApplicationType type);

	List<Application> findAnnualLeaveByStaffAndYear(Staff staff, int year);

	List<Application> getApplicationsForManager(int hierarchy, int departmentId);

	List<Application> getApplicationsForSubordinates(List<Staff> subordinates);

	void approveApplication(Long applicationId);

	void rejectApplication(Long applicationId, String comment);

	Application findApplicationById(String query);

	List<Application> findApplicationByUserId(String query);

	List<Application> findApplicationByName(String query);

	List<Application> findAllApplication();

	List<Application> getAllApplications();

	void updateApplication(Application application);

//	Page<Application> getApplicationsForSubordinates(List<Staff> subordinates, Pageable pageable);
}
