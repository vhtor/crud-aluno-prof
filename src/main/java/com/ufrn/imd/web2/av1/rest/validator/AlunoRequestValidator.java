package com.ufrn.imd.web2.av1.rest.validator;

import com.ufrn.imd.web2.av1.annotation.ValidAlunoCreateRequest;
import com.ufrn.imd.web2.av1.dto.AlunoRequest;
import com.ufrn.imd.web2.av1.rest.context.OnCreate;
import com.ufrn.imd.web2.av1.rest.context.OnUpdate;
import com.ufrn.imd.web2.av1.utils.ValidatorUtils;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class AlunoRequestValidator implements ConstraintValidator<ValidAlunoCreateRequest, AlunoRequest> {
    private Class<?>[] groups;

    @Override
    public void initialize(ValidAlunoCreateRequest constraintAnnotation) {
        this.groups = constraintAnnotation.groups();
    }

    @Override
    public boolean isValid(AlunoRequest alunoRequest,
                           ConstraintValidatorContext constraintValidatorContext
    ) {
        if (Arrays.asList(groups).contains(OnCreate.class)) {
            return isValidCreateRequest(alunoRequest, constraintValidatorContext);
        } else if (Arrays.asList(groups).contains(OnUpdate.class)) {
            return isValidUpdateRequest(alunoRequest, constraintValidatorContext);
        }
        return false;
    }

    public boolean isValidCreateRequest(AlunoRequest alunoRequest,
                                        ConstraintValidatorContext constraintValidatorContext
    ) {
        if (ValidatorUtils.isEmpty(alunoRequest.getNome())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Campo 'nome' é obrigatório")
                    .addPropertyNode("nome")
                    .addConstraintViolation();
            return false;
        }

        if (ValidatorUtils.isEmpty(alunoRequest.getMatricula())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Campo 'matricula' é obrigatório")
                    .addPropertyNode("matricula")
                    .addConstraintViolation();
            return false;
        }

        if (ValidatorUtils.isEmpty(alunoRequest.getCpf())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Campo 'CPF' é obrigatório")
                    .addPropertyNode("cpf")
                    .addConstraintViolation();
            return false;
        }

        if (ValidatorUtils.isEmpty(alunoRequest.getCurso())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Campo 'curso' é obrigatório")
                    .addPropertyNode("curso")
                    .addConstraintViolation();
            return false;
        }

        if (ValidatorUtils.isEmpty(alunoRequest.getGenero())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Campo 'genero' é obrigatório")
                    .addPropertyNode("genero")
                    .addConstraintViolation();
            return false;
        }

        if (ValidatorUtils.isEmpty(alunoRequest.getDataNascimento())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Campo 'dataNascimento' é obrigatório")
                    .addPropertyNode("dataNascimento")
                    .addConstraintViolation();
            return false;
        }

        return true;
    }

    public boolean isValidUpdateRequest(AlunoRequest alunoRequest,
                                        ConstraintValidatorContext constraintValidatorContext
    ) {
        if (alunoRequest.getId() == null || alunoRequest.getId() == 0 || alunoRequest.getId() < 0) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("Campo 'id' precisa ser maior que 0")
                    .addPropertyNode("id")
                    .addConstraintViolation();
            return false;
        }
        return true;
    }
}
