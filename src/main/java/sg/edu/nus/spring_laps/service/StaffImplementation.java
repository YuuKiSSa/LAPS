package sg.edu.nus.spring_laps.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sg.edu.nus.spring_laps.interfacemethods.StaffInterface;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.repository.StaffRepository;

@Service
public class StaffImplementation implements StaffInterface {
    @Autowired
    private StaffRepository staffRepo;

    @Transactional
    public Staff findStaffById(String userId) {
        if (staffRepo.findById(userId).isPresent()) {
            return staffRepo.findById(userId).get();
        }
        return null;
    }
}
