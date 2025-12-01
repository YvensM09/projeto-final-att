package com.desafio.projeto_final.dto;


public class CursoRequestDTO {

    private String nome;
    private String descricao;

    public CursoRequestDTO(){

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
