package sg.edu.nus.spring_laps.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationType;
import sg.edu.nus.spring_laps.model.Staff;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStaff(Staff staff);
    @Query("select a from Application a where year(a.startTime) = :year and a.staff = :staff and a.applicationType.type = 'Medical Leave'")
    List<Application> findMedicalLeaveByStaffAndYear(Staff staff, int year);
    List<Application> findByStaffAndApplicationType(Staff staff, ApplicationType type);
    @Query("select a from Application a where year(a.startTime) = :year and a.staff = :staff and a.applicationType.type = 'Annual Leave'")
    List<Application> findAnnualLeaveByStaffAndYear(Staff staff, int year);
    Page<Application> findAllByStaff(Staff staff, Pageable pageable);
}
