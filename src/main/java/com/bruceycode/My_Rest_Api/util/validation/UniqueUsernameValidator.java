package com.bruceycode.My_Rest_Api.util.validation;

import com.bruceycode.My_Rest_Api.Repository.StudentRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public boolean isValid(String name, ConstraintValidatorContext context) {
        if (name == null) {
            return true; // Let @NotNull handle null values
        }
        return !studentRepository.existsByName(name);
    }
}
