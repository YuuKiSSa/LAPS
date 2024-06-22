package sg.edu.nus.spring_laps.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import sg.edu.nus.spring_laps.model.Application;
import sg.edu.nus.spring_laps.model.ApplicationForm;
import sg.edu.nus.spring_laps.model.Staff;
import sg.edu.nus.spring_laps.service.ApplicationService;
import sg.edu.nus.spring_laps.service.StaffService;

import java.util.List;
import java.util.Objects;

public class RepeatedDaysValidator implements ConstraintValidator<ValidRepeatedDays, ApplicationForm> {
    @Autowired
    ApplicationService applicationService;

    @Autowired
    StaffService staffService;

    @Override
    public void initialize(ValidRepeatedDays constraintAnnotation) {}

    @Override
    public boolean isValid(ApplicationForm form, ConstraintValidatorContext context) {
        Staff staff = staffService.findByUserId(form.getUserId());
        List<Application> applications = applicationService.findApplicationsByStaff(staff);
        for (Application application : applications) {
            if (Objects.equals(application.getId(), form.getApplicationId()) || application.getStatus().equals("Rejected") ||
                    application.getStatus().equals("Deleted") || application.getStatus().equals("Cancel")) {
                continue;
            }
            if ((form.getStartTime().isAfter(application.getStartTime())&&form.getStartTime().isBefore(application.getEndTime()))||
                    (form.getEndTime().isAfter(application.getStartTime()) && form.getEndTime().isBefore(application.getEndTime()))) {
                return false;
            }
        }
        return true;
    }
}
