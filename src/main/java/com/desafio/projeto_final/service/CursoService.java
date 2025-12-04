package com.desafio.projeto_final.service;



import com.desafio.projeto_final.dto.AulaResponseDTO;
import com.desafio.projeto_final.dto.CursoDetailsResponseDTO;
import com.desafio.projeto_final.dto.CursoRequestDTO;
import com.desafio.projeto_final.dto.CursoResponseDTO;
import com.desafio.projeto_final.model.Curso;
import com.desafio.projeto_final.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    public CursoResponseDTO criar (CursoRequestDTO dto) {
        Curso curso = new Curso(dto.getNome(), dto.getDescricao(), dto.isAtivo());
        cursoRepository.save(curso);
        return new CursoResponseDTO(curso.getId(), curso.getNome(), curso.getDescricao(), curso.isAtivo());
    }

    public List<CursoDetailsResponseDTO> listar() {

        return cursoRepository.findAll()
                .stream()
                .map(curso -> {

                    List<AulaResponseDTO> aulasDTO = curso.getAulas()
                            .stream()
                            .map(aula ->
                                    new AulaResponseDTO(
                                            aula.getId(),
                                            aula.getTitulo(),
                                            aula.getDuracaoMinutos(),
                                            aula.getCurso().getId(),
                                            aula.getCurso().getNome()
                                    )
                            )
                            .toList();

                    return new CursoDetailsResponseDTO(
                            curso.getId(),
                            curso.getNome(),
                            curso.getDescricao(),
                            curso.isAtivo(),
                            curso.getAulas().size(),
                            aulasDTO
                    );
                })
                .toList();
    }

    public CursoDetailsResponseDTO buscarCursoDetailsPorId(Long id) {
        Curso curso = cursoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + id));

        List<AulaResponseDTO> aulasDTO = curso.getAulas()
                .stream()
                .map(aula ->
                        new AulaResponseDTO(
                                aula.getId(),
                                aula.getTitulo(),
                                aula.getDuracaoMinutos(),
                                aula.getCurso().getId(),
                                aula.getCurso().getNome()
                        )
                )
                .collect(Collectors.toList());

        return new CursoDetailsResponseDTO(
                curso.getId(),
                curso.getNome(),
                curso.getDescricao(),
                curso.isAtivo(),
                curso.getAulas().size(),
                aulasDTO
        );
    }

    public Curso buscarPorId(Long id){
        return cursoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Curso não encontrado"));
    }

    public CursoResponseDTO atualizar (Long id, CursoRequestDTO dto){
        Curso curso = buscarPorId(id);
        curso.setNome(dto.getNome());
        curso.setDescricao(dto.getDescricao());
        curso.setAtivo(dto.isAtivo());
        cursoRepository.save(curso);

        return new CursoResponseDTO(curso.getId(), curso.getNome(), curso.getDescricao(), curso.isAtivo());
    }

    public void deletar(Long id) {
        Curso curso = buscarPorId(id);
        cursoRepository.delete(curso);
    }
}
