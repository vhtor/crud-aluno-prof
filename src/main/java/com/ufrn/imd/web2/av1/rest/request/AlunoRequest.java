package com.ufrn.imd.web2.av1.rest.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufrn.imd.web2.av1.entity.Aluno;
import com.ufrn.imd.web2.av1.enums.Genero;
import com.ufrn.imd.web2.av1.utils.DateUtils;
import com.ufrn.imd.web2.av1.utils.ValidatorUtils;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AlunoRequest {
    private Long id;
    private Long matricula;
    private String nome;
    private String cpf;
    private String curso;
    private Genero genero;
    private Boolean ativo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", locale = "pt-BR", timezone = "GMT-3")
    private Date dataNascimento;

    @PostConstruct
    public void init() {
        if (ValidatorUtils.isNotEmpty(dataNascimento)) {
            dataNascimento = DateUtils.toStartOfDay(dataNascimento);
        }
    }

    public Aluno toEntity() {
        return Aluno.builder()
                .id(id)
                .matricula(matricula)
                .nome(nome)
                .cpf(cpf)
                .curso(curso)
                .genero(genero)
                .dataNascimento(dataNascimento)
                .build();
    }

    public Aluno toEntityUpdate(Aluno aluno) {
        Optional.ofNullable(this.matricula).ifPresent(aluno::setMatricula);
        Optional.ofNullable(this.nome).ifPresent(aluno::setNome);
        Optional.ofNullable(this.cpf).ifPresent(aluno::setCpf);
        Optional.ofNullable(this.curso).ifPresent(aluno::setCurso);
        Optional.ofNullable(this.genero).ifPresent(aluno::setGenero);
        Optional.ofNullable(this.dataNascimento).ifPresent(aluno::setDataNascimento);

        return aluno;
    }
}
