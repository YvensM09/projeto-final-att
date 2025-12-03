package com.desafio.projeto_final.dto;

import lombok.Data;

@Data
public class CursoRequestDTO {

    private String nome;
    private String descricao;
    private boolean ativo;

    public CursoRequestDTO(){

    }
}
