package sg.edu.nus.spring_laps.service;

import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.Staff;

import java.util.List;
import java.util.Optional;

public interface ApplicationService {
    List<Application> getApplicationsForManager(int managerHierarchy);
    void approveApplication(Long applicationId);
    void rejectApplication(Long applicationId, String comment);
    void saveApplication(Application application); // 添加一个保存方法，以便处理新的字段
	List<Application> getApplicationsForManager(int hierarchy, int departmentId);
	Optional<Application> findById(Long applicationId);
}
