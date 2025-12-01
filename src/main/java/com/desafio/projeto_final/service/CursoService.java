package com.desafio.projeto_final.service;



import com.desafio.projeto_final.dto.AulaResponseDTO;
import com.desafio.projeto_final.dto.CursoDetailsResponseDTO;
import com.desafio.projeto_final.dto.CursoRequestDTO;
import com.desafio.projeto_final.dto.CursoResponseDTO;
import com.desafio.projeto_final.model.Curso;
import com.desafio.projeto_final.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    private final CursoRepository cursoRepository;

    public CursoService(CursoRepository cursoRepository){
        this.cursoRepository = cursoRepository;
    }

    public CursoResponseDTO criar (CursoRequestDTO dto) {
        Curso curso = new Curso(dto.getNome(), dto.getDescricao(), true);
        cursoRepository.save(curso);
        return new CursoResponseDTO(curso.getId(), curso.getNome(), curso.getDescricao(), curso.getAtivo());
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
                            aulasDTO
                    );
                })
                .toList();
    }

    public Curso buscarPorId(Long id){
        return cursoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Curso não encontrado"));
    }

    public CursoResponseDTO atualizar (Long id, CursoRequestDTO dto){
        Curso curso = buscarPorId(id);
        curso.setNome(dto.getNome());
        curso.setDescricao(dto.getDescricao());
        cursoRepository.save(curso);

        return new CursoResponseDTO(curso.getId(), curso.getNome(), curso.getDescricao(), curso.getAtivo());
    }

    public void alterarStatus (Long id, boolean ativo){
        Curso curso = buscarPorId(id);
        curso.setAtivo(ativo);
        cursoRepository.save(curso);
    }


}
