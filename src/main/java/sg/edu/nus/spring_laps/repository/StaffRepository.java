package sg.edu.nus.spring_laps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sg.edu.nus.spring_laps.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, String> {
    Staff findByUserId(String userId);
    Staff findByUserIdAndPassword(String userId, String password);
}
