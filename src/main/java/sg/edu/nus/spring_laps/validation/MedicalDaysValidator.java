package sg.edu.nus.spring_laps.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationForm;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.service.ApplicationService;
import sg.edu.nus.spring_laps.service.StaffService;

import java.time.Duration;
import java.util.List;

public class MedicalDaysValidator implements ConstraintValidator<ValidMedicalLeaveDays, ApplicationForm> {
    @Autowired
    ApplicationService applicationService;
    @Autowired
    StaffService staffService;

    @Override
    public void initialize(ValidMedicalLeaveDays constraintAnnotation) {}

    @Override
    public boolean isValid(ApplicationForm form, ConstraintValidatorContext context) {
        if (!form.getApplicationType().equals("Medical")){
            return true;
        }
        Staff staff = staffService.findByUserId(form.getUserId());
        List<Application> applications = applicationService.findApplicationsByStaff(staff);
        Duration totalDuration = Duration.ZERO;
        for (Application application : applications) {
            totalDuration = totalDuration.plus(Duration.between(application.getStartTime(), application.getEndTime())).plusHours(16);
        }

        Duration totalDurationNow = totalDuration.plus(Duration.between(form.getStartTime(), form.getEndTime()));
        if (totalDurationNow.toDays() <= 60){
            return true;
        }else{
            context.disableDefaultConstraintViolation();
            long daysUsed = totalDurationNow.toDays() - Duration.between(form.getStartTime(), form.getEndTime()).toDays();
            long daysCurrent = 60 - daysUsed;
            String errorMessage = String.format("Your medical leave exceeded: \nTotal used days: %d, \nCurrent used days: %d", daysUsed, daysCurrent);
            context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
            return false;
        }
    }
}
