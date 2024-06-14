package sg.edu.nus.spring_laps.service;

import org.springframework.beans.factory.annotation.Autowired;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.repository.StaffRepository;

public class StaffServiceImplement implements StaffService{
	@Autowired
    private StaffRepository staffRepository;
	
	@Override
    public Staff findByUserId(String userId) {
        return staffRepository.findByUserId(userId);
    }

}
