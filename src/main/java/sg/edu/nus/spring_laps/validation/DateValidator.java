package sg.edu.nus.spring_laps.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sg.edu.nus.spring_laps.model.ApplicationForm;

import java.time.LocalDate;
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
            LocalDate startDate = startTime.toLocalDate();
            LocalDate endDate = endTime.toLocalDate();
            if (!startDate.equals(endDate)){
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Compensation leave should be in one day.").addConstraintViolation();
                return false;
            }else{
                return startTime.isAfter(LocalDateTime.now());
            }
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
