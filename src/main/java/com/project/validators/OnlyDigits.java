package com.project.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = OnlyDigitsValidator.class)
public @interface OnlyDigits {
    String message() default "Only digits required.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
