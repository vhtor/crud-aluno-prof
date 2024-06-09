package com.ufrn.imd.web2.av1.rest.request;

import com.ufrn.imd.web2.av1.entity.Aluno;
import com.ufrn.imd.web2.av1.entity.Professor;
import com.ufrn.imd.web2.av1.entity.Turma;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TurmaRequest {
    private Long id;
    private String nome;
    private String codigo;
    private Long professorId;
    private Collection<Long> alunoIds;

    public Turma toEntity(Professor professor, Collection<Aluno> alunos) {
        return Turma.builder()
                .id(this.id)
                .nome(this.nome)
                .codigo(this.codigo)
                .professor(professor)
                .alunos(alunos)
                .ativo(true)
                .build();
    }
}
