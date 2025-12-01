package com.desafio.projeto_final.controller;



import com.desafio.projeto_final.dto.AulaRequestDTO;
import com.desafio.projeto_final.dto.AulaResponseDTO;
import com.desafio.projeto_final.service.AulaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/aulas")
public class AulaController {

    private final AulaService service;

    public AulaController(AulaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<AulaResponseDTO> criar(@RequestBody AulaRequestDTO dto) {
        AulaResponseDTO aula = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(aula);
    }

    @GetMapping
    public List<AulaResponseDTO> listarTodas() {
        return service.listarTodas();
    }

    @GetMapping("/curso/{cursoId}")
    public List<AulaResponseDTO> listarPorCurso(@PathVariable Long cursoId) {
        return service.listarPorCurso(cursoId);
    }

    @GetMapping("/{id}")
    public AulaResponseDTO buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public AulaResponseDTO atualizar(@PathVariable Long id, @RequestBody AulaRequestDTO dto) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.ok("Aula excluída com sucesso");
    }
}
