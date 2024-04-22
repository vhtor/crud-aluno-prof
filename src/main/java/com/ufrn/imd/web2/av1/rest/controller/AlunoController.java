package com.ufrn.imd.web2.av1.rest.controller;

import com.ufrn.imd.web2.av1.dto.AlunoDTO;
import com.ufrn.imd.web2.av1.dto.AlunoRequest;
import com.ufrn.imd.web2.av1.repository.AlunoRepository;
import com.ufrn.imd.web2.av1.rest.context.OnCreate;
import com.ufrn.imd.web2.av1.rest.context.OnUpdate;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/alunos")
public class AlunoController {
    private final AlunoRepository alunoRepository;

    @GetMapping("/all")
    public List<AlunoDTO> getAll() {
        return alunoRepository.findAll().stream()
                .map(AlunoDTO::of)
                .toList();
    }

    @GetMapping("/{id}")
    public AlunoDTO getById(@PathVariable("id") Long id) {
        return AlunoDTO.of(alunoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno (id: " + id + ") não encontrado"))
        );
    }

    @PostMapping
    public void save(@Validated(OnCreate.class) @RequestBody AlunoRequest request) {
        alunoRepository.save(request.toEntity());
    }

    @PutMapping("/{id}")
    public void update(@Validated(OnUpdate.class) @RequestBody AlunoRequest request) {
        var aluno = alunoRepository.getReferenceById(request.getId());

        aluno.setMatricula(request.getMatricula());
        aluno.setNome(request.getNome());
        aluno.setCpf(request.getCpf());
        aluno.setCurso(request.getCurso());
        aluno.setGenero(request.getGenero());
        aluno.setDataNascimento(request.getDataNascimento());

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
