package sg.edu.nus.spring_laps.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MedicalDaysValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidMedicalLeaveDays {
    String message() default "Your Medical Leave Days are not enough";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
