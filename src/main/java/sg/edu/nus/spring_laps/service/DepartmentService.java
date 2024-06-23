package sg.edu.nus.spring_laps.service;

import sg.edu.nus.spring_laps.model.Department;
import sg.edu.nus.spring_laps.model.Staff;

import java.util.List;

public interface DepartmentService {
    void saveDepartment(Department department);
    List<Department> findAllDepartment();
    List<Staff> findAllStaff(String d);
    Department findByName(String name);
}
