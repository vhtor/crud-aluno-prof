package com.ufrn.imd.web2.av1.rest.controller;

import com.ufrn.imd.web2.av1.dto.AlunoDTO;
import com.ufrn.imd.web2.av1.repository.AlunoRepository;
import com.ufrn.imd.web2.av1.rest.request.AlunoRequest;
import com.ufrn.imd.web2.av1.service.AlunoService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoService alunoService;
    private final AlunoRepository alunoRepository;

    @GetMapping("/all")
    public List<AlunoDTO> getAll() {
        return alunoService.findAllAtivos().stream()
                .map(AlunoDTO::of)
                .toList();
    }

    @GetMapping("/{id}")
    public AlunoDTO getById(@PathVariable("id") Long id) {
        final var aluno = this.alunoService.findAtivoById(id);
        return AlunoDTO.of(aluno);
    }

    @PostMapping
    public void save(@RequestBody AlunoRequest request) {
        this.alunoService.create(request);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody AlunoRequest request) {
        request.setId(id);
        this.alunoService.update(request);
    }

    @PutMapping("/restore/{id}")
    public void restore(@PathVariable("id") Long id) {
        final var aluno = this.alunoService.findById(id);

        aluno.setAtivo(true);
        alunoRepository.save(aluno);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        alunoRepository.deleteById(id);
    }

    @DeleteMapping("/logic/{id}")
    public void deleteLogic(@PathVariable("id") Long id) {
        var aluno = alunoRepository.getReferenceById(id);

        aluno.setAtivo(false);
        alunoRepository.save(aluno);
    }
}
