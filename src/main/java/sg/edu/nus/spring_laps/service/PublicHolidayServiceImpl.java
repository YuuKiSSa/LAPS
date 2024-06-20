package sg.edu.nus.spring_laps.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sg.edu.nus.spring_laps.model.PublicHoliday;
import sg.edu.nus.spring_laps.repository.PublicHolidayRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PublicHolidayServiceImpl implements PublicHolidayService {
    @Autowired
    private PublicHolidayRepository publicHolidayRepository;

    @Override
    public List<LocalDate> findAllPublicHolidays() {
        List<PublicHoliday> publicHolidays =  publicHolidayRepository.findAll();
        List<LocalDate> publicHolidayDates = new ArrayList<LocalDate>();
        for (PublicHoliday publicHoliday : publicHolidays) {
            publicHolidayDates.add(publicHoliday.getDate());
        }
        return publicHolidayDates;
    }

}