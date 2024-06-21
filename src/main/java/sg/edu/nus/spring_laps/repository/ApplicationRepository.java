package sg.edu.nus.spring_laps.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.Staff;

import java.time.LocalDateTime;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStaff_Hierarchy(int hierarchy);

	Page<Application> findByStaff_HierarchyAndStaff_Department_Id(int subordinateHierarchy, int departmentId,Pageable pageable);
	List<Application> findByStaff(Staff staff);
	Page<Application> findByStaffIn(List<Staff> staff,Pageable pageable);
	List<Application> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    List<Application> findByStartTimeBetweenAndApplicationType_Type(LocalDateTime startTime, LocalDateTime endTime, String type);

    List<Application> findByStaffUserIdAndApplicationType_Type(String userId, String type);

    List<Application> findByApplicationType_Type(String type);

}
