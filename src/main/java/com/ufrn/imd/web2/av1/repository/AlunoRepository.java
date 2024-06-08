package com.ufrn.imd.web2.av1.repository;

import com.ufrn.imd.web2.av1.entity.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Optional<Aluno> findByMatricula(Long matricula);

    Optional<Aluno> findByCpf(String cpf);

    Optional<Aluno> findByIdAndAtivoIsTrue(Long id);

    Collection<Aluno> findAllByAtivoTrue();

    Collection<Aluno> findAllByIdInAndAtivoIsTrue(Collection<Long> alunoIds);
}
