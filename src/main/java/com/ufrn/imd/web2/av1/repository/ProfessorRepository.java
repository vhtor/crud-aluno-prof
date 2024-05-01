package com.ufrn.imd.web2.av1.repository;

import com.ufrn.imd.web2.av1.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByIdAndAtivoIsTrue(Long id);

    Collection<Professor> findAllByAtivoTrue();

    Optional<Professor> findByMatriculaAndAtivoIsTrue(Long matricula);

    Optional<Professor> findByCpf(String cpf);
}
