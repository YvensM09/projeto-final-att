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
    public ResponseEntity<CursoDetailsResponseDTO> buscarPorId (@PathVariable Long id){
        CursoDetailsResponseDTO dto = service.buscarCursoDetailsPorId(id);
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public CursoResponseDTO atualizar (@PathVariable Long id, @RequestBody CursoRequestDTO dto){
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.ok("Curso deletado com sucesso!");
    }
}
