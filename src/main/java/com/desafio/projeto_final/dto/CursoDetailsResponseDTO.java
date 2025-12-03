package com.desafio.projeto_final.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CursoDetailsResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private boolean ativo;
    private int quantidadeAulas;
    private List<AulaResponseDTO> aulas;
}
