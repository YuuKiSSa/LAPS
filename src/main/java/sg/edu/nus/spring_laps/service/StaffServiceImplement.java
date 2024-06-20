package sg.edu.nus.spring_laps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.repository.StaffRepository;

@Service
public class StaffServiceImplement implements StaffService{
	@Autowired
    private StaffRepository staffRepository;
	
	@Override
    public Staff findByUserId(String userId) {
        return staffRepository.findByUserId(userId);
    }
	public List<Staff> getSubordinates(int departmentId,int managerHierarchy) {
        return staffRepository.findByDepartmentIdAndHierarchyLessThan(departmentId,managerHierarchy);
    }
}
