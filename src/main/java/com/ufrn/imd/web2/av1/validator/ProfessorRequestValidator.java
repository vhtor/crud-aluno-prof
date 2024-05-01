package com.ufrn.imd.web2.av1.validator;

import com.ufrn.imd.web2.av1.common.Validator;
import com.ufrn.imd.web2.av1.entity.Professor;
import com.ufrn.imd.web2.av1.enums.Genero;
import com.ufrn.imd.web2.av1.exception.DataValidationException;
import com.ufrn.imd.web2.av1.rest.enums.Context;
import com.ufrn.imd.web2.av1.rest.request.ProfessorRequest;
import com.ufrn.imd.web2.av1.service.ProfessorService;
import com.ufrn.imd.web2.av1.utils.DateUtils;
import com.ufrn.imd.web2.av1.utils.ValidatorUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProfessorRequestValidator implements Validator {
    private Professor professor;
    private Context context;
    private ProfessorRequest request;
    private ProfessorService service;

    @Override
    public void validate() {
        if (context == Context.CREATE) {
            validateCreate(request);
        } else {
            validateUpdate(request);
        }
    }

    private void validateCreate(ProfessorRequest request) {
        validarMatricula(request.getMatricula());
        validarNome(request.getNome());
        validarCpf(request.getCpf());
        validarDepartamento(request.getDepartamento());
        validarDisciplinaAssociada(request.getDisciplinaAssociada());
        validarGenero(request.getGenero());
        validarDataNascimento(request.getDataNascimento());
        validarSalario(request.getSalario());
    }

    private void validateUpdate(ProfessorRequest request) {
        if (ValidatorUtils.isNotEmpty(request.getNome())) {
            this.validarNome(request.getNome());
        }

        if (ValidatorUtils.isNotEmpty(request.getCpf())) {
            this.validarCpf(request.getCpf());
        }

        if (ValidatorUtils.isNotEmpty(request.getDepartamento())) {
            this.validarDepartamento(request.getDepartamento());
        }

        if (ValidatorUtils.isNotEmpty(request.getDisciplinaAssociada())) {
            this.validarDisciplinaAssociada(request.getDisciplinaAssociada());
        }

        if (ValidatorUtils.isNotEmpty(request.getGenero())) {
            this.validarGenero(request.getGenero());
        }

        if (ValidatorUtils.isNotEmpty(request.getDataNascimento())) {
            this.validarDataNascimento(request.getDataNascimento());
        }

        if (ValidatorUtils.isNotEmpty(request.getMatricula())) {
            this.validarMatricula(request.getMatricula());
        }

        if (ValidatorUtils.isNotEmpty(request.getSalario())) {
            this.validarSalario(request.getSalario());
        }
    }

    private void validarMatricula(Long matricula) {
        if (ValidatorUtils.isEmpty(matricula)) {
            throw new DataValidationException("Matrícula é obrigatória");
        }


        final var professorByMatricula = service.findByMatricula(matricula);
        if (professorByMatricula.isPresent()) {
            if (this.context.equals(Context.UPDATE)
                    && !Objects.equals(professorByMatricula.get().getId(), this.professor.getId())) {
                throw new DataValidationException("Já existe um professor(a) com esta matrícula cadastrada");
            }

            throw new DataValidationException("Já existe um professor(a) com esta matrícula cadastrada");
        }

    }

    private void validarNome(String nome) {
        if (ValidatorUtils.isEmpty(nome)) {
            throw new DataValidationException("Nome é obrigatório");
        }
    }

    private void validarCpf(String cpf) {
        if (ValidatorUtils.isEmpty(cpf)) {
            throw new DataValidationException("CPF é obrigatório");
        }

        final var professorByCpf = service.findByCpf(cpf);
        if (professorByCpf.isPresent()) {
            if (this.context.equals(Context.UPDATE)
                    && !Objects.equals(professorByCpf.get().getId(), this.professor.getId())) {
                throw new DataValidationException("Já existe um professor(a) com este CPF cadastrado");
            }

            throw new DataValidationException("Já existe um professor(a) com este CPF cadastrado");
        }
    }

    private void validarDepartamento(String departamento) {
        if (ValidatorUtils.isEmpty(departamento)) {
            throw new DataValidationException("Departamento é obrigatório");
        }
    }

    private void validarDisciplinaAssociada(String disciplinaAssociada) {
        if (ValidatorUtils.isEmpty(disciplinaAssociada)) {
            throw new DataValidationException("Disciplina associada é obrigatória");
        }
    }

    private void validarGenero(Genero genero) {
        if (ValidatorUtils.isEmpty(genero)) {
            throw new DataValidationException("Gênero é obrigatório");
        }

        if (genero != Genero.FEMININO && genero != Genero.MASCULINO) {
            throw new DataValidationException("Gênero inválido. Valores aceitos: FEMININO, MASCULINO");
        }
    }

    private void validarDataNascimento(Date dataNascimento) {
        if (ValidatorUtils.isEmpty(dataNascimento)) {
            throw new DataValidationException("Data de nascimento é obrigatória");
        }

        final var dataMinima = DateUtils.on(1, 1, 1900);
        final var dateFormatter = new SimpleDateFormat("dd/MM/yyyy");

        if (dataNascimento.before(dataMinima)) {
            throw new DataValidationException(
                    "Data de nascimento não pode ser menor que " + dateFormatter.format(dataMinima.getTime()));
        }

        if (dataNascimento.after(new Date())) {
            throw new DataValidationException("Data de nascimento não pode ser maior que a data atual");
        }
    }

    private void validarSalario(BigDecimal salario) {
        if (ValidatorUtils.isEmpty(salario)) {
            throw new DataValidationException("Salário é obrigatório");
        }
    }
}
