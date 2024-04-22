package com.ufrn.imd.web2.av1.dto;

import com.ufrn.imd.web2.av1.entity.Aluno;
import com.ufrn.imd.web2.av1.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AlunoDTO {
    private Long id;
    private Long matricula;
    private String nome;
    private String cpf;
    private String curso;
    private Genero genero;
    private Date dataNascimento;

    public static AlunoDTO of(Aluno entity) {
        return AlunoDTO.builder()
                .id(entity.getId())
                .matricula(entity.getMatricula())
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .curso(entity.getCurso())
                .genero(entity.getGenero())
                .dataNascimento(entity.getDataNascimento())
                .build();
    }
}
