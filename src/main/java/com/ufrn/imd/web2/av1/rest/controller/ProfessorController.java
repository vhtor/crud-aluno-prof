package com.ufrn.imd.web2.av1.rest.controller;

import com.ufrn.imd.web2.av1.dto.ProfessorDTO;
import com.ufrn.imd.web2.av1.repository.ProfessorRepository;
import com.ufrn.imd.web2.av1.rest.request.ProfessorRequest;
import com.ufrn.imd.web2.av1.service.ProfessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProfessorController {
    private final ProfessorService professorService;
    private final ProfessorRepository professorRepository;

    @GetMapping("/all")
    public List<ProfessorDTO> getAll() {
        return professorService.findAllAtivos().stream()
                .map(ProfessorDTO::of)
                .toList();
    }

    @GetMapping
    public ProfessorDTO getById(Long id) {
        final var professor = this.professorService.findAtivoById(id);
        return ProfessorDTO.of(professor);
    }

    @PostMapping
    @Transactional
    public void save(@RequestBody ProfessorRequest request) {
        this.professorService.create(request);
    }

    @PutMapping("/{id}")
    @Transactional
    public void update(Long id, @RequestBody ProfessorRequest request) {
        request.setId(id);
        this.professorService.update(request);
    }

    @PutMapping("/restore/{id}")
    @Transactional
    public void restore(Long id) {
        final var professor = this.professorService.findById(id);

        professor.setAtivo(true);
        professorRepository.save(professor);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable("id") Long id) {
        final var professor = this.professorService.findById(id);

        professorRepository.delete(professor);
    }

    @DeleteMapping("/logic/{id}")
    @Transactional
    public void deleteLogic(@PathVariable("id") Long id) {
        final var professor = this.professorService.findAtivoById(id);

        professor.setAtivo(false);
        professorRepository.save(professor);
    }
}
