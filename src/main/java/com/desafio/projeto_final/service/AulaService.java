package com.desafio.projeto_final.service;



import com.desafio.projeto_final.dto.AulaRequestDTO;
import com.desafio.projeto_final.dto.AulaResponseDTO;
import com.desafio.projeto_final.model.Aula;
import com.desafio.projeto_final.model.Curso;
import com.desafio.projeto_final.repository.AulaRepository;
import com.desafio.projeto_final.repository.CursoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AulaService {

    private final AulaRepository aulaRepository;
    private final CursoRepository cursoRepository;

    public AulaService(AulaRepository aulaRepository, CursoRepository cursoRepository) {
        this.aulaRepository = aulaRepository;
        this.cursoRepository = cursoRepository;
    }

    public AulaResponseDTO criar(AulaRequestDTO dto) {
        Curso curso = cursoRepository.findById(dto.getCursoId())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + dto.getCursoId()));

        Aula aula = new Aula(dto.getTitulo(), dto.getDuracaoMinutos(), curso);
        aulaRepository.save(aula);

        return toResponseDTO(aula);
    }

    public List<AulaResponseDTO> listarTodas() {
        return aulaRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public List<AulaResponseDTO> listarPorCurso(Long cursoId) {
        // Valida se o curso existe
        cursoRepository.findById(cursoId)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + cursoId));

        return aulaRepository.findByCursoId(cursoId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    public AulaResponseDTO buscarPorId(Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada com ID: " + id));
        return toResponseDTO(aula);
    }

    public AulaResponseDTO atualizar(Long id, AulaRequestDTO dto) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada com ID: " + id));

        // Se o cursoId foi alterado, valida se o novo curso existe
        if (dto.getCursoId() != null && !dto.getCursoId().equals(aula.getCurso().getId())) {
            Curso novoCurso = cursoRepository.findById(dto.getCursoId())
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado com ID: " + dto.getCursoId()));
            aula.setCurso(novoCurso);
        }

        aula.setTitulo(dto.getTitulo());
        aula.setDuracaoMinutos(dto.getDuracaoMinutos());
        aulaRepository.save(aula);

        return toResponseDTO(aula);
    }

    public void excluir(Long id) {
        Aula aula = aulaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aula não encontrada com ID: " + id));
        aulaRepository.delete(aula);
    }

    private AulaResponseDTO toResponseDTO(Aula aula) {
        return new AulaResponseDTO(
                aula.getId(),
                aula.getTitulo(),
                aula.getDuracaoMinutos(),
                aula.getCurso().getId(),
                aula.getCurso().getNome()
        );
    }
}
