package sg.edu.nus.spring_laps.service;

import sg.edu.nus.spring_laps.model.Staff;

public interface StaffService {
	Staff findByUserId(String userId);
}
