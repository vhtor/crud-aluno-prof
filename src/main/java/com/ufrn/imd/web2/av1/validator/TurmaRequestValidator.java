package com.ufrn.imd.web2.av1.validator;

import com.ufrn.imd.web2.av1.common.Validator;
import com.ufrn.imd.web2.av1.entity.Turma;
import com.ufrn.imd.web2.av1.exception.DataValidationException;
import com.ufrn.imd.web2.av1.rest.enums.Context;
import com.ufrn.imd.web2.av1.rest.request.TurmaRequest;
import com.ufrn.imd.web2.av1.service.TurmaService;
import com.ufrn.imd.web2.av1.utils.ValidatorUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TurmaRequestValidator implements Validator {
    private Turma turma;
    private Context context;
    private TurmaRequest request;
    private TurmaService service;

    @Override
    public void validate() {
        if (context == Context.CREATE) {
            validateCreate(request);
        } else {
            validateUpdate(request);
        }
    }

    private void validateCreate(TurmaRequest request) {
        validarNome(request.getNome());
        validarCodigo(request.getCodigo());
        validarProfessor(request.getProfessorId());
        validarAlunos(request.getAlunoIds());
        validarExistente(request.getNome(), request.getCodigo());
    }

    private void validarExistente(String nome, String codigo) {
        final var turmaByNomeAndCodigo = this.service.findByNomeAndCodigo(nome, codigo);

        if (ValidatorUtils.isNotEmpty(turmaByNomeAndCodigo)) {
            throw new DataValidationException("Já existe uma turma com o nome e código informados");
        }
    }

    private void validateUpdate(TurmaRequest request) {
        if (ValidatorUtils.isNotEmpty(request.getNome())) {
            this.validarNome(request.getNome());
        }

        if (ValidatorUtils.isNotEmpty(request.getCodigo())) {
            this.validarCodigo(request.getCodigo());
        }

        if (ValidatorUtils.isNotEmpty(request.getProfessorId())) {
            this.validarProfessor(request.getProfessorId());
        }

        if (ValidatorUtils.isNotEmpty(request.getAlunoIds())) {
            this.validarAlunos(request.getAlunoIds());
        }

        if (ValidatorUtils.isNotEmpty(request.getNome()) && ValidatorUtils.isNotEmpty(request.getCodigo())) {
            validarExistente(request.getNome(), request.getCodigo());
        }
    }

    private void validarNome(String nome) {
        if (ValidatorUtils.isEmpty(nome)) {
            throw new DataValidationException("O nome da turma é obrigatório");
        }


    }

    private void validarCodigo(String codigo) {
        if (ValidatorUtils.isEmpty(codigo)) {
            throw new DataValidationException("O código da turma é obrigatório");
        }
    }

    private void validarProfessor(Long professorId) {
    }

    private void validarAlunos(Collection<Long> alunoIds) {
    }
}
