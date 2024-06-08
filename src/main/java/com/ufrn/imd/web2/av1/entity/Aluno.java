package com.ufrn.imd.web2.av1.entity;

import com.ufrn.imd.web2.av1.enums.Genero;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "alunos")
@Builder(toBuilder = true)
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;
    private Long matricula;
    private String nome;
    private String cpf;
    private String curso;
    private Date dataNascimento;
    private boolean ativo;

    @Enumerated(EnumType.STRING)
    private Genero genero;

    @ManyToMany(fetch = FetchType.LAZY)
    private Collection<Turma> turmas;
}
