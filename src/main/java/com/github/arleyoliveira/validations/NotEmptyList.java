package com.github.arleyoliveira.validations;

import com.github.arleyoliveira.validations.constraintvalidation.NotEmptyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmptyList {
    String message () default "The list cannot empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
