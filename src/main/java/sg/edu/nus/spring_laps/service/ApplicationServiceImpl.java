package sg.edu.nus.spring_laps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.repository.ApplicationRepository;
import sg.edu.nus.spring_laps.service.ApplicationService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    @Override
    public List<Application> getApplicationsForManager(int managerHierarchy) {
        int subordinateHierarchy = managerHierarchy - 1;
        return applicationRepository.findByStaff_Hierarchy(subordinateHierarchy);
    }

    @Override
    public List<Application> getApplicationsForManager(int hierarchy, int departmentId) {
        int subordinateHierarchy = hierarchy - 1;
        return applicationRepository.findByStaff_HierarchyAndStaff_Department_Id(subordinateHierarchy, departmentId);
    }

    @Override
    public void approveApplication(Long applicationId) {
        Application application = applicationRepository.findById(applicationId).orElseThrow();
        application.setStatus("Approved");
        applicationRepository.save(application);
    }

    @Override
    public void rejectApplication(Long applicationId, String comment) {
        Application application = applicationRepository.findById(applicationId).orElseThrow();
        application.setStatus("Rejected");
        applicationRepository.save(application);
    }

    @Override
    public void saveApplication(Application application) {
        applicationRepository.save(application);
    }

    @Override
    public Optional<Application> findById(Long applicationId) {
        return applicationRepository.findById(applicationId);
    }
    @Override
    public List<Application> getApplicationsForSubordinates(List<Staff> subordinates) {
        return applicationRepository.findByStaffIn(subordinates);
    }
}
