package com.ufrn.imd.web2.av1.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "turmas")
@Builder(toBuilder = true)
public class Turma {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private String nome;
    private String codigo;
    private boolean ativo;

    @ManyToMany(mappedBy = "turmas", fetch = FetchType.LAZY)
    private Collection<Aluno> alunos;

    @OneToOne(fetch = FetchType.LAZY)
    private Professor professor;

    public Turma toEntityUpdate(Professor professor, Collection<Aluno> alunos) {
        return this.toBuilder()
                .professor(professor)
                .alunos(alunos)
                .build();
    }
}
