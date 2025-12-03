package com.desafio.projeto_final.dto;


public class AulaResponseDTO {

    private Long id;
    private String titulo;
    private int duracaoMinutos;
    private Long cursoId;
    private String cursoNome;

    public AulaResponseDTO(Long id, String titulo, int duracaoMinutos, Long cursoId, String cursoNome) {
        this.id = id;
        this.titulo = titulo;
        this.duracaoMinutos = duracaoMinutos;
        this.cursoId = cursoId;
        this.cursoNome = cursoNome;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public String getCursoNome() {
        return cursoNome;
    }
}
