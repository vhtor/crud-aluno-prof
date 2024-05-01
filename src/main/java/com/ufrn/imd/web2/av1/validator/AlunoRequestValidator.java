package com.ufrn.imd.web2.av1.validator;

import com.ufrn.imd.web2.av1.common.Validator;
import com.ufrn.imd.web2.av1.entity.Aluno;
import com.ufrn.imd.web2.av1.enums.Genero;
import com.ufrn.imd.web2.av1.exception.DataValidationException;
import com.ufrn.imd.web2.av1.rest.enums.Context;
import com.ufrn.imd.web2.av1.rest.request.AlunoRequest;
import com.ufrn.imd.web2.av1.service.AlunoService;
import com.ufrn.imd.web2.av1.utils.DateUtils;
import com.ufrn.imd.web2.av1.utils.ValidatorUtils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoRequestValidator implements Validator {
    private Aluno aluno;
    private Context context;
    private AlunoRequest request;
    private AlunoService service;

    @Override
    public void validate() {
        if (context == Context.CREATE) {
            validateCreate(request);
        } else {
            validateUpdate(request);
        }
    }

    public void validateUpdate(AlunoRequest request) {
        if (ValidatorUtils.isNotEmpty(request.getNome())) {
            this.validarNome(request.getNome());
        }

        if (ValidatorUtils.isNotEmpty(request.getCpf())) {
            this.validarCpf(request.getCpf());
        }

        if (ValidatorUtils.isNotEmpty(request.getCurso())) {
            this.validarCurso(request.getCurso());
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
    }

    public void validateCreate(AlunoRequest request) {
        this.validarNome(request.getNome());
        this.validarCpf(request.getCpf());
        this.validarCurso(request.getCurso());
        this.validarGenero(request.getGenero());
        this.validarDataNascimento(request.getDataNascimento());
        this.validarMatricula(request.getMatricula());
    }

    public void validarNome(String nome) {
        if (ValidatorUtils.isEmpty(nome)) {
            throw new DataValidationException("Nome é obrigatório");
        }
    }

    public void validarCpf(String cpf) {
        if (ValidatorUtils.isEmpty(cpf)) {
            throw new DataValidationException("CPF é obrigatório");
        }

        final var alunoByCpf = service.findByCpf(cpf);
        if (alunoByCpf.isPresent()) {
            if (this.context.equals(Context.UPDATE) && !Objects.equals(alunoByCpf.get().getId(), aluno.getId())) {
                throw new DataValidationException("Já existe um aluno(a) com este CPF cadastrado");
            }

            throw new DataValidationException("Já existe um aluno(a) com este CPF cadastrado");
        }
    }

    public void validarCurso(String curso) {
        if (ValidatorUtils.isEmpty(curso)) {
            throw new DataValidationException("Curso é obrigatório");
        }
    }

    public void validarGenero(Genero genero) {
        if (ValidatorUtils.isEmpty(genero)) {
            throw new DataValidationException("Gênero é obrigatório");
        }

        if (genero != Genero.FEMININO && genero != Genero.MASCULINO && genero != Genero.OUTRO) {
            throw new DataValidationException("Gênero inválido. Valores aceitos: FEMININO, MASCULINO, OUTRO");
        }
    }

    public void validarDataNascimento(Date dataNascimento) {
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

    public void validarMatricula(Long matricula) {
        if (ValidatorUtils.isEmpty(matricula)) {
            throw new DataValidationException("Matrícula é obrigatória");
        }

        final var alunoByMatricula = service.findByMatricula(matricula);
        if (alunoByMatricula.isPresent()) {
            if (this.context.equals(Context.UPDATE) && !Objects.equals(alunoByMatricula.get().getId(), aluno.getId())) {
                throw new DataValidationException("Já existe um aluno(a) com esta matrícula cadastrada");
            }

            throw new DataValidationException("Já existe um aluno(a) com esta matrícula cadastrada");
        }
    }
}
