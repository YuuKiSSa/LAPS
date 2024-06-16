package sg.edu.nus.spring_laps.service;

import sg.edu.nus.spring_laps.model.Admin;
import sg.edu.nus.spring_laps.model.Staff;

public interface StaffService {
	Admin authenticateAdmin(String userId, String password);
    Staff authenticateStaff(String userId, String password);
}
