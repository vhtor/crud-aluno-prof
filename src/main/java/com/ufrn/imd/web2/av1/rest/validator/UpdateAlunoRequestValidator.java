package com.ufrn.imd.web2.av1.rest.validator;

import com.ufrn.imd.web2.av1.annotation.ValidAlunoUpdateRequest;
import com.ufrn.imd.web2.av1.dto.AlunoRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UpdateAlunoRequestValidator implements ConstraintValidator<ValidAlunoUpdateRequest, AlunoRequest> {
    @Override
    public boolean isValid(AlunoRequest alunoRequest,
                           ConstraintValidatorContext constraintValidatorContext
    ) {
        if (alunoRequest.getId() == null || alunoRequest.getId() == 0 || alunoRequest.getId() < 0){
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Campo 'id' precisa ser maior que 0")
                    .addPropertyNode("id")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }
}
