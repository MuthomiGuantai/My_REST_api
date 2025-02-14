package com.bruceycode.My_Rest_Api.util.Validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidEmailDomainValidator.class)
public @interface ValidEmailDomain {
    String message() default "Invalid email domain";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
