package sg.edu.nus.spring_laps.service;

import java.util.List;

import sg.edu.nus.spring_laps.model.PublicHoliday;

public interface PublicHolidayService {
	public List<PublicHoliday> findAllHoliday();
	public void saveHoliday(PublicHoliday publicHoliday);
}
