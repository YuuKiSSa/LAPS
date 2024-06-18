package sg.edu.nus.spring_laps.service;

import sg.edu.nus.spring_laps.model.PublicHoliday;

import java.time.LocalDate;
import java.util.List;

public interface PublicHolidayService {
    List<LocalDate> findAllPublicHolidays();
}
