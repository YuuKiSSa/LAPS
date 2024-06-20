package sg.edu.nus.spring_laps.service;

import java.util.List;

import sg.edu.nus.spring_laps.model.Staff;

public interface StaffService {
	Staff findByUserId(String userId);
	List<Staff> getSubordinates(int managerHierarchy,int departmentId);
}
