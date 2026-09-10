package com.rahim.syntopicalnotes.domains.validation;

import java.lang.reflect.Field;
import java.time.LocalDate;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateAfterValidator implements ConstraintValidator<DateAfter, Object> {

    private String fromField;
    private String toField;

    @Override
    public void initialize(DateAfter annotation) {
        this.fromField = annotation.from();
        this.toField = annotation.to();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext ctx) {
        if (value == null) {
            return true;
        }
        try {
            LocalDate from = (LocalDate) getField(value, fromField);
            LocalDate to   = (LocalDate) getField(value, toField);

            if (from == null || to == null) {
                return true;
            }

            return to.isAfter(from);
        } catch (NoSuchFieldException | IllegalAccessException | ClassCastException e) {
            return false;
        }
    }

    private Object getField(Object target, String name)
            throws NoSuchFieldException, IllegalAccessException {
        Field field = target.getClass().getDeclaredField(name);
        field.setAccessible(true);
        return field.get(target);
    }
}
