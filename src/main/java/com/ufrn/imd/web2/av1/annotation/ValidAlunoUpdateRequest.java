package com.ufrn.imd.web2.av1.annotation;

import com.ufrn.imd.web2.av1.rest.validator.UpdateAlunoRequestValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Constraint(validatedBy = UpdateAlunoRequestValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAlunoUpdateRequest {
    String message() default "Invalid AlunoRequest";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
