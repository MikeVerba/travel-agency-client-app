package com.example.travelagencyclientapp.app.validators;



import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;


@Component
public class EqualFieldsValidator implements ConstraintValidator<EqualFields, Object> {

    private String baseField;
    private String matchField;



    @Override
    public void initialize(EqualFields constraint) {
        baseField = constraint.baseField();
        matchField = constraint.matchField();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext constraintValidatorContext) {

        try {

            Object baseFieldValue = getFieldValue(object,baseField);
            Object matchFieldValue = getFieldValue(object,matchField);
            return baseFieldValue != null && baseFieldValue.equals(matchFieldValue);

        } catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }

    private Object getFieldValue(Object object, String fieldName) throws Exception {
        Class<?> clazz = object.getClass();
        Field passwordField = clazz.getDeclaredField(fieldName);
        passwordField.setAccessible(true);
        return passwordField.get(object);
    }
}
