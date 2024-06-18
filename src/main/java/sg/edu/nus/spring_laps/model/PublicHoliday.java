package sg.edu.nus.spring_laps.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class PublicHoliday {
    @Id
    private LocalDate date;
    @Column(unique=true, length=50, nullable=false)
    private String holidayName;

    public PublicHoliday() {}

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getHolidayName() {
        return holidayName;
    }

    public void setHolidayName(String holidayName) {
        this.holidayName = holidayName;
    }

    @Override
    public String toString() {
        return "publicHoliday{" +
                "date=" + date +
                ", holiday='" + holidayName + '\'' +
                '}';
    }
}
