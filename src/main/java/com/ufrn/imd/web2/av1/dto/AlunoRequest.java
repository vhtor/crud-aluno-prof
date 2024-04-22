package com.ufrn.imd.web2.av1.dto;

import com.ufrn.imd.web2.av1.entity.Aluno;
import com.ufrn.imd.web2.av1.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AlunoRequest {
    private Long id;
    private Long matricula;
    private String nome;
    @CPF
    private String cpf;
    private String curso;
    private Genero genero;
    private Date dataNascimento;

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
}
