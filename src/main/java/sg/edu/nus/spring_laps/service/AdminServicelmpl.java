package sg.edu.nus.spring_laps.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import java.util.Optional;
//import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Admin;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.spring_laps.model.Admin;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.repository.AdminRepository;
import sg.edu.nus.spring_laps.repository.AdministratorRepository;

@Service
@Transactional(readOnly = false)
public class AdminServicelmpl implements AdminService{
	@Autowired
	private AdminRepository adminRepository;
	@Autowired
	private AdministratorRepository administratorRepository;
    @Override
    @Transactional
	public List<Staff> findStaffByName(String name) {
		// TODO Auto-generated method stub
		return adminRepository.findStaffsByName(name);
	}

    @Override
    @Transactional
	public List<Staff> findStaffByHierarchy(String hierarchy) {
		// TODO Auto-generated method stub
		return adminRepository.findStaffsByHierarchy(hierarchy);
	}

    @Override
    @Transactional
	public void saveStaff(Staff staff) {
		// TODO Auto-generated method stub
		adminRepository.save(staff);
	}
    
	@Override
	@Transactional
	public Staff saveStaffs(Staff staff) {
		// TODO Auto-generated method stub
		return adminRepository.save(staff);
	}
    @Override
    @Transactional
	public Staff findStaffByUserId(String id) {
    	 if (adminRepository.findById(id).isPresent()) {
             return adminRepository.findById(id).get();
         }
         return null;
	}

    @Override
    @Transactional
	public void deleteStaff(String userId) {
		// TODO Auto-generated method stub
		adminRepository.deleteById(userId);
		adminRepository.flush();
	}

    @Override
    @Transactional
	public List<Staff> findAllStaff() {
		// TODO Auto-generated method stub
		return adminRepository.findAll();
	}

    @Override
    @Transactional
	public List<Integer> findAllHierarchy() {
		// TODO Auto-generated method stub
		return adminRepository.findAllHierarchy();
	}
    
    @Override
    @Transactional
    public Admin findAdminById(String id) {
        Optional<Admin> optionalAdmin = administratorRepository.findById(id);
        if (optionalAdmin.isPresent()) {
            return optionalAdmin.get();
        } else {
            return null;
        }
    }


	@Override
	public void saveAdmin(Admin admin) {
		// TODO Auto-generated method stub
		administratorRepository.save(admin);
	}

	@Override
	public void deleteAdmin(String userId) {
		// TODO Auto-generated method stub
		administratorRepository.deleteById(userId);
		administratorRepository.flush();
	}



}



