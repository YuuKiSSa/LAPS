package sg.edu.nus.spring_laps.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.spring_laps.model.PublicHoliday;
import sg.edu.nus.spring_laps.repository.PublicHolidayRepository;

@Service
@Transactional(readOnly = false)
public class PublicHolidayServicelmpl implements PublicHolidayService{

	@Autowired
	private PublicHolidayRepository publicHolidayRepository;
	@Override
	public List<PublicHoliday> findAllHoliday() {
		// TODO Auto-generated method stub
		return publicHolidayRepository.findAll();
	}
	@Override
	public void saveHoliday(PublicHoliday publicHoliday) {
		// TODO Auto-generated method stub
		publicHolidayRepository.save(publicHoliday);
	}

}
