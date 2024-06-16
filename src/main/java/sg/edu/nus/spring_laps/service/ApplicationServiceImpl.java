package sg.edu.nus.spring_laps.service;

import org.springframework.beans.factory.annotation.Autowired;
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

}
