package com.ufrn.imd.web2.av1.rest.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufrn.imd.web2.av1.entity.Professor;
import com.ufrn.imd.web2.av1.enums.Genero;
import com.ufrn.imd.web2.av1.utils.DateUtils;
import com.ufrn.imd.web2.av1.utils.ValidatorUtils;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfessorRequest {
    private Long id;
    private Long matricula;
    private String nome;
    private String cpf;
    private String departamento;
    private String disciplinaAssociada;
    private Genero genero;
    private Boolean ativo;
    private BigDecimal salario;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "GMT-3")
    private Date dataNascimento;

    @PostConstruct
    public void init() {
        if (ValidatorUtils.isNotEmpty(dataNascimento)) {
            dataNascimento = DateUtils.toStartOfDay(dataNascimento);
        }
    }

    public Professor toEntity() {
        return Professor.builder()
                .id(id)
                .matricula(matricula)
                .nome(nome)
                .cpf(cpf)
                .departamento(departamento)
                .disciplinaAssociada(disciplinaAssociada)
                .salario(salario)
                .genero(genero)
                .dataNascimento(dataNascimento)
                .ativo(ativo)
                .build();
    }

    public Professor toEntityUpdate(Professor professor) {
        Optional.ofNullable(this.matricula).ifPresent(professor::setMatricula);
        Optional.ofNullable(this.nome).ifPresent(professor::setNome);
        Optional.ofNullable(this.cpf).ifPresent(professor::setCpf);
        Optional.ofNullable(this.departamento).ifPresent(professor::setDepartamento);
        Optional.ofNullable(this.disciplinaAssociada).ifPresent(professor::setDisciplinaAssociada);
        Optional.ofNullable(this.salario).ifPresent(professor::setSalario);
        Optional.ofNullable(this.genero).ifPresent(professor::setGenero);
        Optional.ofNullable(this.dataNascimento).ifPresent(professor::setDataNascimento);

        return professor;
    }
}
