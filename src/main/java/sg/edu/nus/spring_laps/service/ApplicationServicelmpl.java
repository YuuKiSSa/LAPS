package sg.edu.nus.spring_laps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationType;
import sg.edu.nus.spring_laps.repository.ApplicationRepository;
import sg.edu.nus.spring_laps.repository.ApplicationTypeRepository;
@Service
@Transactional(readOnly = false)
public class ApplicationServicelmpl implements ApplicationService {
	@Autowired
	private ApplicationRepository applicationRepository;
	@Autowired
	private ApplicationTypeRepository applicationTypeRepository;

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
	public void saveApplication(Application application) {
		// TODO Auto-generated method stub
		applicationRepository.save(application);
	}

	@Override
	public List<Application> getAllApplications() {
		// TODO Auto-generated method stub
		return applicationRepository.findAll();
	}
	
	public void updateApplication(Application application) {
		ApplicationType applicationType = applicationTypeRepository.findById(application.getApplicationType().getId()).orElse(null);
		System.out.println(applicationType);
        if (applicationType != null) {
            application.setApplicationType(applicationType);
            applicationRepository.save(application);
	}
        }
}
