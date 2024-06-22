package sg.edu.nus.spring_laps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sg.edu.nus.spring_laps.model.Department;
import sg.edu.nus.spring_laps.model.Staff;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    @Query("SELECT s FROM Staff s WHERE s.department.id = :departmentId")
    public List<Staff> findAllStaff(String departmentId);

    public Department findByName(String name);
}
