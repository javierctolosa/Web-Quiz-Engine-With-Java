package engine.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = MaxListSizeValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxListSize {
    String message() default "Value exceeds the maximum list size";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
