package sg.edu.nus.spring_laps.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import sg.edu.nus.spring_laps.model.PublicHoliday;

import java.time.LocalDate;

public interface PublicHolidayRepository extends JpaRepository<PublicHoliday, LocalDate> {
}
