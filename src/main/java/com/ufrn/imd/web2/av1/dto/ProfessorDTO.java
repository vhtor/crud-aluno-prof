package com.ufrn.imd.web2.av1.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ufrn.imd.web2.av1.entity.Professor;
import com.ufrn.imd.web2.av1.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessorDTO {
    private Long id;
    private Long matricula;
    private String nome;
    private String cpf;
    private String departamento;
    private String disciplinaAssociada;
    private BigDecimal salario;
    private Genero genero;
    private boolean ativo;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dataNascimento;

    public static ProfessorDTO of(Professor entity) {
        return ProfessorDTO.builder()
                .id(entity.getId())
                .matricula(entity.getMatricula())
                .nome(entity.getNome())
                .cpf(entity.getCpf())
                .departamento(entity.getDepartamento())
                .disciplinaAssociada(entity.getDisciplinaAssociada())
                .salario(entity.getSalario())
                .genero(entity.getGenero())
                .dataNascimento(entity.getDataNascimento())
                .build();
    }
}
