package com.ufrn.imd.web2.av1.service;

import com.ufrn.imd.web2.av1.entity.Aluno;
import com.ufrn.imd.web2.av1.exception.ResourceNotFoundException;
import com.ufrn.imd.web2.av1.repository.AlunoRepository;
import com.ufrn.imd.web2.av1.rest.enums.Context;
import com.ufrn.imd.web2.av1.rest.request.AlunoRequest;
import com.ufrn.imd.web2.av1.validator.AlunoRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AlunoService {
    private final AlunoRepository alunoRepository;

    @Transactional
    public void create(AlunoRequest request) {
        final var validator = AlunoRequestValidator.builder()
                .context(Context.CREATE)
                .request(request)
                .service(this)
                .build();

        validator.validate();
        this.alunoRepository.save(request.toEntity());
    }

    @Transactional
    public void update(AlunoRequest request) {
        final var aluno = this.findAtivoById(request.getId());
        final var validator = AlunoRequestValidator.builder()
                .context(Context.UPDATE)
                .aluno(aluno)
                .request(request)
                .service(this)
                .build();

        validator.validate();
        alunoRepository.save(request.toEntityUpdate(aluno));
    }

    public Aluno findAtivoById(Long id) {
        return this.alunoRepository.findByIdAndAtivoIsTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno (id: " + id + ") não encontrado"));
    }

    public Aluno findById(Long id) {
        return this.alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno (id: " + id + ") não encontrado"));
    }

    public Collection<Aluno> findAllAtivos() {
        return this.alunoRepository.findAllByAtivoTrue();
    }

    public Optional<Aluno> findByMatricula(Long matricula) {
        return this.alunoRepository.findByMatricula(matricula);
    }

    public Optional<Aluno> findByCpf(String cpf) {
        return this.alunoRepository.findByCpf(cpf);
    }
}
