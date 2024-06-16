package sg.edu.nus.spring_laps.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.spring_laps.repository.AdminRepository;
import sg.edu.nus.spring_laps.repository.StaffRepository;
import sg.edu.nus.spring_laps.service.StaffService;
import sg.edu.nus.spring_laps.model.Admin;
import sg.edu.nus.spring_laps.model.Staff;


@Service
public class StaffServiceImpl implements StaffService{
	@Autowired
    private AdminRepository adminRepository;

    @Autowired
    private StaffRepository staffRepository;
    
    @Override
    public Admin authenticateAdmin(String userId, String password) {
        return adminRepository.findByUserIdAndPassword(userId, password);
    }

    @Override
    public Staff authenticateStaff(String userId, String password) {
        return staffRepository.findByUserIdAndPassword(userId, password);
    }
}
