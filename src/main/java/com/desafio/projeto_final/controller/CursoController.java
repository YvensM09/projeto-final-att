package com.desafio.projeto_final.controller;

import com.desafio.projeto_final.dto.CursoDetailsResponseDTO;
import com.desafio.projeto_final.dto.CursoRequestDTO;
import com.desafio.projeto_final.dto.CursoResponseDTO;
import com.desafio.projeto_final.model.Curso;
import com.desafio.projeto_final.service.CursoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService service;

    public CursoController (CursoService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> criar (@RequestBody CursoRequestDTO dto){

        service.criar(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping
    public List<CursoDetailsResponseDTO> listar(){
        return service.listar();
    }

    @GetMapping("/{id}")
    public Curso buscarPorId (@PathVariable Long id){
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public CursoResponseDTO
    atualizar (@PathVariable Long id, @RequestBody CursoRequestDTO dto){
        return service.atualizar(id, dto);
    }

    @PatchMapping("/{id}/status")
    public String alterarStatus(@PathVariable Long id, @RequestParam boolean ativo){
        service.alterarStatus(id, ativo);
        return ativo ? "Curso ativado": "Curso desativdo";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok("Curso deletado com sucesso!");
    }
}
