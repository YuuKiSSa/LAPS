package sg.edu.nus.spring_laps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationType;
import sg.edu.nus.spring_laps.model.Staff;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStaff(Staff staff);
    @Query("select a from Application a where year(a.startTime) = :year and a.staff = :staff")
    List<Application> findByStaffAndYear(Staff staff, int year);
    List<Application> findByStaffAndApplicationType(Staff staff, ApplicationType type);
}
