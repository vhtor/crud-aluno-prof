package com.ufrn.imd.web2.av1.service;

import com.ufrn.imd.web2.av1.entity.Professor;
import com.ufrn.imd.web2.av1.exception.ResourceNotFoundException;
import com.ufrn.imd.web2.av1.repository.ProfessorRepository;
import com.ufrn.imd.web2.av1.rest.enums.Context;
import com.ufrn.imd.web2.av1.rest.request.ProfessorRequest;
import com.ufrn.imd.web2.av1.validator.ProfessorRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ProfessorService {
    private final ProfessorRepository professorRepository;

    @Transactional
    public void create(ProfessorRequest request) {
        final var validator = ProfessorRequestValidator.builder()
                .context(Context.CREATE)
                .request(request)
                .service(this)
                .build();

        validator.validate();
        professorRepository.save(request.toEntity());
    }

    @Transactional
    public void update(ProfessorRequest request) {
        final var professor = this.findAtivoById(request.getId());
        final var validator = ProfessorRequestValidator.builder()
                .context(Context.UPDATE)
                .professor(professor)
                .request(request)
                .service(this)
                .build();

        validator.validate();
        professorRepository.save(request.toEntityUpdate(professor));
    }

    public Professor findAtivoById(Long id) {
        return this.professorRepository.findByIdAndAtivoIsTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor (id: " + id + ") não encontrado"));
    }

    public Professor findById(Long id) {
        return this.professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor (id: " + id + ") não encontrado"));
    }

    public Collection<Professor> findAllAtivos() {
        return this.professorRepository.findAllByAtivoTrue();
    }

    public Optional<Professor> findByMatricula(Long matricula) {
        return this.professorRepository.findByMatriculaAndAtivoIsTrue(matricula);
    }

    public Optional<Professor> findByCpf(String cpf) {
        return this.professorRepository.findByCpf(cpf);
    }
}
