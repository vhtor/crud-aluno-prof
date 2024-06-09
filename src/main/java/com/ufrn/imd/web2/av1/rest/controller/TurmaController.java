package com.ufrn.imd.web2.av1.rest.controller;

import com.ufrn.imd.web2.av1.dto.TurmaDTO;
import com.ufrn.imd.web2.av1.repository.TurmaRepository;
import com.ufrn.imd.web2.av1.rest.request.TurmaRequest;
import com.ufrn.imd.web2.av1.service.TurmaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/turmas")
public class TurmaController {
    private final TurmaService turmaService;
    private final TurmaRepository turmaRepository;

    @GetMapping("/all")
    public List<TurmaDTO> getAll() {
        return turmaService.findAllAtivos().stream()
                .map(TurmaDTO::of)
                .toList();
    }

    @GetMapping("/{id}")
    public TurmaDTO getById(@PathVariable("id") Long id) {
        final var turma = this.turmaService.findAtivaById(id);
        return TurmaDTO.of(turma);
    }

    @PostMapping
    public void save(@RequestBody TurmaRequest request) {
        this.turmaService.create(request);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody TurmaRequest request) {
        request.setId(id);
        this.turmaService.update(request);
    }

    @PutMapping("/restore/{id}")
    public void restore(@PathVariable("id") Long id) {
        final var turma = this.turmaService.findById(id);

        turma.setAtivo(true);
        turmaRepository.save(turma);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        final var turma = this.turmaService.findById(id);

        turmaRepository.delete(turma);
    }

    @DeleteMapping("/logic/{id}")
    public void deleteLogic(@PathVariable("id") Long id) {
        final var turma = this.turmaService.findById(id);

        turma.setAtivo(false);
        turmaRepository.save(turma);
    }
}
