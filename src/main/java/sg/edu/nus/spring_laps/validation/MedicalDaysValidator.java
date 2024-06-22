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

public class MedicalDaysValidator implements ConstraintValidator<ValidMedicalLeaveDays, ApplicationForm> {
    @Autowired
    ApplicationService applicationService;
    @Autowired
    StaffService staffService;

    @Override
    public void initialize(ValidMedicalLeaveDays constraintAnnotation) {}

    @Override
    public boolean isValid(ApplicationForm form, ConstraintValidatorContext context) {
        if (!form.getApplicationType().equals("Medical Leave")){
            return true;
        }
        Staff staff = staffService.findByUserId(form.getUserId());
        List<Application> applications = applicationService.findMedicalLeaveByStaffAndYear(staff, form.getStartTime().getYear());
        int medicalLeaveDays = 0;
        for (Application application : applications) {
            if (Objects.equals(application.getId(), form.getApplicationId()) || application.getStatus().equals("Rejected") ||
                    application.getStatus().equals("Deleted") || application.getStatus().equals("Cancel")) {
                continue;
            }
            medicalLeaveDays += application.getEndTime().getDayOfYear() - application.getStartTime().getDayOfYear() + 1;
        }
        int medicalLeaveDaysNow = form.getEndTime().getDayOfYear() - form.getStartTime().getDayOfYear() + 1;
        if (medicalLeaveDays + medicalLeaveDaysNow <= 60){
            return true;
        }else{
            context.disableDefaultConstraintViolation();
            long daysCurrent = 60 - medicalLeaveDays;
            String errorMessage = String.format("Your medical leave exceeded in this year: \nTotal used days: %d, \nCurrent used days: %d", medicalLeaveDays, daysCurrent);
            context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();
            return false;
        }
    }
}
