package com.example.travelagencyclientapp.app.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = EqualFieldsValidator.class)
public @interface EqualFields {
    String message() default "Passwords must match each other";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String baseField();
    String matchField();

}
