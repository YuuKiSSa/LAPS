package sg.edu.nus.spring_laps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.spring_laps.model.Application;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStaff_Hierarchy(int hierarchy);

	List<Application> findByStaff_HierarchyAndStaff_Department_Id(int subordinateHierarchy, int departmentId);
}
