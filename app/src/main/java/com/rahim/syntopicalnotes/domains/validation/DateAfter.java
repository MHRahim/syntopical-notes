package com.rahim.syntopicalnotes.domains.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = DateAfterValidator.class)
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DateAfter {

    String message() default "must be after the previous date";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String from();

    String to();
}
