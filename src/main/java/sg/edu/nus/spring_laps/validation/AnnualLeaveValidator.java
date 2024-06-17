package sg.edu.nus.spring_laps.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import sg.edu.nus.spring_laps.model.ApplicationForm;

public class AnnualLeaveValidator implements ConstraintValidator<ValidAnnualLeave, ApplicationForm> {
    @Override
    public void initialize(ValidAnnualLeave constraintAnnotation) {}

    @Override
    public boolean isValid(ApplicationForm form, ConstraintValidatorContext constraintValidatorContext) {
        return true;
    }
}
