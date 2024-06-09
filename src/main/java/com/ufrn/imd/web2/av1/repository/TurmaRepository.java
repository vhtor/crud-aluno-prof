package com.ufrn.imd.web2.av1.repository;

import com.ufrn.imd.web2.av1.entity.Turma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface TurmaRepository extends JpaRepository<Turma, Long> {
    Optional<Turma> findByIdAndAtivoTrue(Long id);

    Collection<Turma> findAllByAtivoTrue();

    Optional<Turma> findByNomeIgnoreCaseAndCodigoIgnoreCaseAndAtivoTrue(String nome, String codigo);
}
