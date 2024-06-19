package sg.edu.nus.spring_laps.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import sg.edu.nus.spring_laps.controller.ApplicationController;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationForm;
import sg.edu.nus.spring_laps.model.Department;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.service.ApplicationService;
import sg.edu.nus.spring_laps.service.PublicHolidayService;
import sg.edu.nus.spring_laps.service.StaffService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class AnnualLeaveValidator implements ConstraintValidator<ValidAnnualLeave, ApplicationForm> {
    @Autowired
    StaffService staffService;
    @Autowired
    ApplicationService applicationService;
    @Autowired
    PublicHolidayService publicHolidayService;

    @Override
    public void initialize(ValidAnnualLeave constraintAnnotation) {}

    @Override
    public boolean isValid(ApplicationForm form, ConstraintValidatorContext context) {
        if (!form.getApplicationType().equals("Annual Leave")) {
            return true;
        }
        Staff staff = staffService.findByUserId(form.getUserId());
        int annualLeaveDays = staff.getDepartment().getAnnualLeave();
        List<Application> applications = applicationService.findAnnualLeaveByStaffAndYear(staff, form.getStartTime().getYear());
        if (isHoliday(form.getStartTime().toLocalDate()) || isHoliday(form.getEndTime().toLocalDate())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Start date and end date should be workday. Please check again.").addConstraintViolation();
            return false;
        }
        if (annualLeaveDays > 14){
            for (Application application : applications) {
                if (Objects.equals(application.getId(), form.getApplicationId()) || application.getStatus().equals("Rejected") ||
                        application.getStatus().equals("Deleted") || application.getStatus().equals("Cancel")) {
                    continue;
                }
                int days = application.getEndTime().getDayOfYear() - application.getStartTime().getDayOfYear() + 1;
                annualLeaveDays -= days;
            }
            annualLeaveDays -= form.getEndTime().getDayOfYear() - form.getStartTime().getDayOfYear() + 1;
        }else {
            for (Application application : applications) {
                if (Objects.equals(application.getId(), form.getApplicationId()) || application.getStatus().equals("Rejected") ||
                        application.getStatus().equals("Deleted") || application.getStatus().equals("Cancel")) {
                    continue;
                }
                int days = application.getEndTime().getDayOfYear() - application.getStartTime().getDayOfYear() + 1;
                for (int i = 0; i < days; i++) {
                    LocalDate date = application.getStartTime().toLocalDate().plusDays(i);
                    if (!isHoliday(date)) {
                       annualLeaveDays--;
                    }
                }
            }
            int formDays = form.getEndTime().getDayOfYear() - form.getStartTime().getDayOfYear() + 1;
            for (int i = 0; i < formDays; i++) {
                LocalDate date = form.getStartTime().toLocalDate().plusDays(i);
                if (!isHoliday(date)) {
                    annualLeaveDays--;
                }
            }
        }

        if (annualLeaveDays < 0){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Your annual leave is not enough.").addConstraintViolation();
            return false;
        }
        return true;
    }

    public boolean isHoliday(LocalDate date) {
        List<LocalDate> publicHolidaysDates = publicHolidayService.findAllPublicHolidays();
        if (publicHolidaysDates.contains(date) || date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY) {
            return true;
        }
        return false;
    }
}

