package sg.edu.nus.spring_laps.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.spring_laps.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, String> {
    // 根据邮箱查找员工
    Staff findByEmail(String email);
    Staff findByName(String name);
	Staff findByUserId(String userId);
	List<Staff> findByHierarchyGreaterThan(int hierarchy);
}
