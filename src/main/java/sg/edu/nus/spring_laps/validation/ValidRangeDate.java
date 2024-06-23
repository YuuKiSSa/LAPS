package sg.edu.nus.spring_laps.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = DateValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRangeDate {
    String message() default "Invalid Time! Please select a valid time.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
