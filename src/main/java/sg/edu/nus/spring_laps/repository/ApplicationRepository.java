package sg.edu.nus.spring_laps.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationType;
import sg.edu.nus.spring_laps.model.Staff;

import java.time.LocalDateTime;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStaff(Staff staff);

    @Query("select a from Application a where year(a.startTime) = :year and a.staff = :staff and a.applicationType.type = 'Medical Leave'")
    List<Application> findMedicalLeaveByStaffAndYear(Staff staff, int year);

    List<Application> findByStaffAndApplicationType(Staff staff, ApplicationType type);

    @Query("select a from Application a where year(a.startTime) = :year and a.staff = :staff and a.applicationType.type = 'Annual Leave'")
    List<Application> findAnnualLeaveByStaffAndYear(Staff staff, int year);

    Page<Application> findAllByStaff(Staff staff, Pageable pageable);

    List<Application> findByStaff_HierarchyAndStaff_Department_Id(int subordinateHierarchy, int departmentId);

    List<Application> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    @Query("SELECT a FROM Application a WHERE a.startTime BETWEEN :startTime AND :endTime AND a.applicationType.type = :applicationType")
    List<Application> findByStartTimeBetweenAndApplicationType(LocalDateTime startTime, LocalDateTime endTime, String applicationType);

    @Query("SELECT a FROM Application a WHERE a.staff.userId = :userId AND a.applicationType.type = :type")
    List<Application> findByStaffUserIdAndApplicationType(String userId, String type);

    @Query("SELECT a FROM Application a WHERE a.applicationType.type = :applicationType")
    List<Application> findByApplicationType(String applicationType);

    List<Application> findByStaffIn(List<Staff> staff);

    @Query("select a from Application a where a.staff.userId= :query")
    public List<Application> findApplicationByUserId(String query);

    @Query("select a from Application a where a.staff.name= :query")
    public List<Application> findApplicationByName(String query);
    
//    Page<Application> findByStaffIn(List<Staff> staff, Pageable pageable);
}
