package sg.edu.nus.spring_laps.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationForm;
import sg.edu.nus.spring_laps.service.ApplicationService;

import java.util.List;

public class MedicalDaysValidator implements ConstraintValidator<ValidMedicalLeaveDays, ApplicationForm> {
    @Autowired
    ApplicationService applicationService;

    @Override
    public void initialize(ValidMedicalLeaveDays constraintAnnotation) {}

    @Override
    public boolean isValid(ApplicationForm form, ConstraintValidatorContext context) {
        //List<Application> applications = applicationService.findApplicationsByStaff();
        return true;
    }
}
