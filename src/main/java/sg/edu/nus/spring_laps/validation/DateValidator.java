package sg.edu.nus.spring_laps.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sg.edu.nus.spring_laps.model.ApplicationForm;

import java.time.LocalDateTime;

public class DateValidator implements ConstraintValidator<ValidRangeDate, ApplicationForm> {
    @Override
    public void initialize(ValidRangeDate constraintAnnotation) {}

    @Override
    public boolean isValid(ApplicationForm form, ConstraintValidatorContext context) {
        LocalDateTime startTime = form.getStartTime();
        LocalDateTime endTime = form.getEndTime();
        if (startTime == null || endTime == null) {
            return true;
        }
        if(form.getApplicationType().equals("Compensation Leave")){
            return true;
        }
        if (startTime.equals(endTime)){
            return true;
        }

        if (!startTime.isBefore(endTime)) {
            return false;
        }else{
            return startTime.isAfter(LocalDateTime.now());
        }
    }
}
