package sg.edu.nus.spring_laps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.Staff;

import java.sql.Date;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStaff_Hierarchy(int hierarchy);

	List<Application> findByStaff_HierarchyAndStaff_Department_Id(int subordinateHierarchy, int departmentId);
	List<Application> findByStaff(Staff staff);
	List<Application> findByStaffIn(List<Staff> staff);
	
	List<Application> findByStartTimeBetween(Date startTime, Date endTime);

    List<Application> findByStartTimeBetweenAndApplicationType_Type(Date startTime, Date endTime, String type);

    List<Application> findByStaffUserIdAndApplicationType_Type(String userId, String type);

    List<Application> findByApplicationType_Type(String type);

}
