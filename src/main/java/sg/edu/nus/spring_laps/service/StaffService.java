package sg.edu.nus.spring_laps.service;

import sg.edu.nus.spring_laps.model.Admin;
import sg.edu.nus.spring_laps.model.Staff;

import java.util.List;

public interface StaffService {
    Staff findByUserId(String userId);
    Admin authenticateAdmin(String userId, String password);
    Staff authenticateStaff(String userId, String password);
    List<Staff> getSubordinates(int managerHierarchy);
}
