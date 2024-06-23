package sg.edu.nus.spring_laps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.nus.spring_laps.model.Department;
import sg.edu.nus.spring_laps.model.Staff;

import java.util.List;

public interface StaffRepository extends JpaRepository<Staff, String> {
    Staff findByUserId(String userId);
    Staff findByUserIdAndPassword(String userId, String password);
    List<Staff> findByDepartmentIdAndHierarchyLessThan(int departmentId, int hierarchy);
    @Query("SELECT s FROM Staff s WHERE s.name = :name")
    List<Staff> findStaffsByName(String name);

    @Query("SELECT s FROM Staff s WHERE s.hierarchy = :hierarchy")
    List<Staff> findStaffsByHierarchy(String hierarchy);

    Staff findStaffByUserId(String userId);

    @Query("SELECT s FROM Staff s WHERE s.department = :d")
    List<Staff> findAllStaff(Department d);

    @Query("SELECT distinct s.hierarchy FROM Staff s")
    public List<Integer> findAllHierarchy();

    @Modifying
    @Transactional
    public void deleteById(String userId);
}
