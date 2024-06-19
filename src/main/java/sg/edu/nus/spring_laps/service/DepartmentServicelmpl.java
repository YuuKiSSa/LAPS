package sg.edu.nus.spring_laps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.spring_laps.model.Department;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.repository.DepartmentRepository;


@Service
@Transactional(readOnly = false)
public class DepartmentServicelmpl implements DepartmentService{
	
	@Autowired
	private DepartmentRepository departmentRepository;
	
	@Override
	public List<Department> findAllDepartment() {
		// TODO Auto-generated method stub
		return departmentRepository.findAll();
	}

	@Override
	public List<Staff> findAllStaff(String d) {
		// TODO Auto-generated method stub
		return departmentRepository.findAllStaff(d);
	}

	@Override
	public Department findByName(String name) {
		// TODO Auto-generated method stub
		return departmentRepository.findByName(name);
	}

	@Override
	public void saveDepartment(Department department) {
		// TODO Auto-generated method stub
		departmentRepository.save(department);
	}
}
