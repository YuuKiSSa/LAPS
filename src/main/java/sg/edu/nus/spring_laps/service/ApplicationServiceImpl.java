package sg.edu.nus.spring_laps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationType;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.repository.ApplicationRepository;
import sg.edu.nus.spring_laps.repository.ApplicationTypeRepository;

import java.util.List;

@Service
@Transactional
public class ApplicationServiceImpl implements ApplicationService {
    @Autowired
    private ApplicationRepository applicationRepository;
    @Autowired
    private ApplicationTypeRepository applicationTypeRepository;

    @Override
    public Application saveApplication(Application application) {
        return applicationRepository.save(application);
    }

    @Override
    public List<ApplicationType> findAllApplicationTypes() {
        return applicationTypeRepository.findAll();
    }

    @Override
    public ApplicationType findApplicationTypeByName(String applicationTypeName){
        return applicationTypeRepository.findApplicationTypeByName(applicationTypeName);
    }

    @Override
    public Application findApplicationById(Long applicationId) {
        if (applicationRepository.existsById(applicationId)) {
            return applicationRepository.findById(applicationId).get();
        }
        return null;
    }
    @Override
    public List<Application> findApplicationsByStaff(Staff staff) {
        return applicationRepository.findByStaff(staff);
    }
    @Override
    public ApplicationType findApplicationTypeById(int ApplicationTypeId) {
        return applicationTypeRepository.findById(ApplicationTypeId).get();
    }
    @Override
    public List<Application> findMedicalLeaveByStaffAndYear(Staff staff, int year){
        return applicationRepository.findMedicalLeaveByStaffAndYear(staff, year);
    }
    @Override
    public List<Application> findApplicationsByStaffAndApplicationType(Staff staff, ApplicationType type) {
        return applicationRepository.findByStaffAndApplicationType(staff, type);
    }
    @Override
    public List<Application> findAnnualLeaveByStaffAndYear(Staff staff, int year){
        return applicationRepository.findAnnualLeaveByStaffAndYear(staff, year);
    }
    @Override
    public Page<Application> findAllByStaff(Staff staff, Pageable pageable) {
        return applicationRepository.findAllByStaff(staff, pageable);
    }
    @Override
    public List<Application> getApplicationsForManager(int hierarchy, int departmentId) {
        int subordinateHierarchy = hierarchy - 1;
        return applicationRepository.findByStaff_HierarchyAndStaff_Department_Id(subordinateHierarchy, departmentId);
    }
    @Override
    public List<Application> getApplicationsForSubordinates(List<Staff> subordinates) {
        return applicationRepository.findByStaffIn(subordinates);
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

    public Application findApplicationById(String query) {
        try {
            long id = Long.parseLong(query); // 将String转换为long
            return applicationRepository.findById(id).orElse(null); // 返回Optional的值或者null
        } catch (NumberFormatException e) {
            // 处理转换失败的情况，例如返回一个错误或null
            System.out.println("Invalid input: the query must be a valid number.");
            return null; // 或者抛出一个自定义异常
        }
    }

    @Override
    public List<Application> findApplicationByUserId(String query) {
        // TODO Auto-generated method stub
        return applicationRepository.findApplicationByUserId(query);
    }

    @Override
    public List<Application> findApplicationByName(String query) {
        // TODO Auto-generated method stub
        return applicationRepository.findApplicationByName(query);
    }

    @Override
    public List<Application> findAllApplication() {
        // TODO Auto-generated method stub
        return applicationRepository.findAll();
    }

    @Override
    public List<Application> getAllApplications() {
        // TODO Auto-generated method stub
        return applicationRepository.findAll();
    }
    
//    @Override
//    public Page<Application> getApplicationsForSubordinates(List<Staff> subordinates, Pageable pageable) {
//        return applicationRepository.findByStaffIn(subordinates, pageable);
//    }

    public void updateApplication(Application application) {
        ApplicationType applicationType = applicationTypeRepository.findById(application.getApplicationType().getId()).orElse(null);
        System.out.println(applicationType);
        if (applicationType != null) {
            application.setApplicationType(applicationType);
            applicationRepository.save(application);
        }
    }
}
