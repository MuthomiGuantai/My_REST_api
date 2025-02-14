package com.bruceycode.My_Rest_Api.util.Validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class ValidEmailDomainValidator implements ConstraintValidator<ValidEmailDomain, String> {

    private static final List<String> ALLOWED_DOMAINS = Arrays.asList("example.com", "gmail.com");

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) {
            return true; // Let @NotNull handle null values
        }
        String domain = email.substring(email.indexOf('@') + 1);
        return ALLOWED_DOMAINS.contains(domain);
    }
}
