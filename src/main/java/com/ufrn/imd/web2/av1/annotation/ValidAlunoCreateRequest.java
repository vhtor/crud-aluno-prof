package com.ufrn.imd.web2.av1.annotation;

import com.ufrn.imd.web2.av1.rest.validator.AlunoRequestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = AlunoRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAlunoCreateRequest {
    String message() default "Invalid AlunoRequest";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
