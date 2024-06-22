package sg.edu.nus.spring_laps.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RepeatedDaysValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidRepeatedDays {
    String message() default "The leave you applied for has duplicate dates.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
