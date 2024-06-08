package com.ufrn.imd.web2.av1.dto;

import com.ufrn.imd.web2.av1.entity.Turma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TurmaDTO {
    private Long id;
    private String nome;
    private String codigo;
    private ProfessorDTO professor;
    private Collection<AlunoDTO> alunos;

    public static TurmaDTO of(Turma turma) {
        return builder()
                .id(turma.getId())
                .nome(turma.getNome())
                .codigo(turma.getCodigo())
                .professor(ProfessorDTO.of(turma.getProfessor()))
                .alunos(turma.getAlunos().stream()
                        .map(AlunoDTO::of)
                        .collect(Collectors.toList()))
                .build();
    }
}
