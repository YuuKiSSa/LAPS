package sg.edu.nus.spring_laps.service;

import sg.edu.nus.spring_laps.model.Admin;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.dto.StaffDTO;

import java.util.List;

public interface AdminService {
    public List<Staff> findStaffByName(String name);
    public List<Staff> findStaffByHierarchy(String hierarchy);
    public void saveStaff(Staff staff);
    public Staff saveStaffs(Staff staff);
    public Staff findStaffByUserId(String UserId);
    public void deleteStaff(String userId);
    public List<Staff> findAllStaff();
    public List<Integer> findAllHierarchy();
    public List<StaffDTO> findAllStaffWithout();
    public Admin findAdminById(String id);
    public void saveAdmin(Admin admin);
    public void deleteAdmin(String userId);
}
