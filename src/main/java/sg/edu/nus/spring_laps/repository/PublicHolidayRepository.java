package sg.edu.nus.spring_laps.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import sg.edu.nus.spring_laps.model.PublicHoliday;

public interface PublicHolidayRepository extends JpaRepository<PublicHoliday,LocalDate>{
	
}
