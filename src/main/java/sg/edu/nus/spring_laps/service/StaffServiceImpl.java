package sg.edu.nus.spring_laps.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.spring_laps.model.Admin;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.repository.AdminRepository;
import sg.edu.nus.spring_laps.repository.StaffRepository;

@Service
@Transactional
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private AdminRepository adminRepository;

    @Override
    public Staff findByUserId(String userId) {
        if (staffRepository.findById(userId).isPresent()) {
            return staffRepository.findById(userId).get();
        }
        return null;
    }

    @Override
    public Admin authenticateAdmin(String userId, String password) {
        return adminRepository.findByUserIdAndPassword(userId, password);
    }

    @Override
    public Staff authenticateStaff(String userId, String password) {
        return staffRepository.findByUserIdAndPassword(userId, password);
    }


}
