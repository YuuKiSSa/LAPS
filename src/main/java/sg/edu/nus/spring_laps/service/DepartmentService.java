package sg.edu.nus.spring_laps.service;

import java.util.List;

import sg.edu.nus.spring_laps.model.Department;
import sg.edu.nus.spring_laps.model.Staff;

public interface DepartmentService {
public void saveDepartment(Department department);
public List<Department> findAllDepartment();
public List<Staff> findAllStaff(String d);
public Department findByName(String name);
}
