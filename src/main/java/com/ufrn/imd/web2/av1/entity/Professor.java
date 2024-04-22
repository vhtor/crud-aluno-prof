package com.ufrn.imd.web2.av1.entity;

import com.ufrn.imd.web2.av1.enums.Genero;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "professores")
@Builder
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long matricula;
    private String nome;
    private String cpf;
    private Genero genero;
    private String departamento;
    private String disciplinaAssociada;
    private Date dataNascimento;
    private BigDecimal salario;
    private boolean ativo;
}
