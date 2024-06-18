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

    List<Application> findByStartTimeBetweenAndApplicationtype(Date startTime, Date endTime, String applicationtype);

    List<Application> findByStaffAndApplicationtype(Staff staff, String type);

    List<Application> findByApplicationtype(String applicationtype);

}
