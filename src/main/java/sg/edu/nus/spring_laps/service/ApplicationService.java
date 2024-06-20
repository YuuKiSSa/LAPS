package sg.edu.nus.spring_laps.service;

import java.util.List;

import sg.edu.nus.spring_laps.model.Application;

public interface ApplicationService {
	public Application findApplicationById(String query);
	public List<Application> findApplicationByUserId(String query);
	public List<Application> findApplicationByName(String query);
	public List<Application> findAllApplication();
	public void saveApplication(Application application);
	public List<Application> getAllApplications();
	public void updateApplication(Application application);
}
