package com.ufrn.imd.web2.av1.service;

import com.ufrn.imd.web2.av1.entity.Turma;
import com.ufrn.imd.web2.av1.exception.ResourceNotFoundException;
import com.ufrn.imd.web2.av1.repository.TurmaRepository;
import com.ufrn.imd.web2.av1.rest.enums.Context;
import com.ufrn.imd.web2.av1.rest.request.TurmaRequest;
import com.ufrn.imd.web2.av1.validator.TurmaRequestValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TurmaService {
    private final ProfessorService professorService;
    private final AlunoService alunoService;

    private final TurmaRepository turmaRepository;

    @Transactional
    public void create(TurmaRequest request) {
        final var validator = TurmaRequestValidator.builder()
                .context(Context.CREATE)
                .request(request)
                .service(this)
                .build();

        validator.validate();

        final var professor = this.professorService.findById(request.getProfessorId());
        final var alunos = this.alunoService.findAllByIds(request.getAlunoIds());

        turmaRepository.save(request.toEntity(professor, alunos));
    }

    @Transactional
    public void update(TurmaRequest request) {
        final var turma = this.findAtivaById(request.getId());

        final var validator = TurmaRequestValidator.builder()
                .context(Context.UPDATE)
                .turma(turma)
                .request(request)
                .service(this)
                .build();

        validator.validate();

        final var professor = this.professorService.findById(request.getProfessorId());
        final var alunos = this.alunoService.findAllByIds(request.getAlunoIds());

        turmaRepository.save(turma.toEntityUpdate(professor, alunos));
    }

    public Turma findAtivaById(Long id) {
        return this.turmaRepository.findByIdAndAtivoTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turma (id: " + id + ") não encontrada"));
    }

    public Turma findById(Long id) {
        return this.turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turma (id: " + id + ") não encontrada"));
    }

    public Collection<Turma> findAllAtivos() {
        return this.turmaRepository.findAllByAtivoTrue();
    }
}
