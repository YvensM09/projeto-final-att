package com.desafio.projeto_final.dto;



public class CursoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;
    private boolean ativo;

    public CursoResponseDTO(Long id, String nome, String descricao, boolean ativo){
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean getAtivo() {
        return ativo;
    }
}
