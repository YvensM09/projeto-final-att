package com.desafio.projeto_final.model;


import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table (name = "cursos")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nome;
    private String descricao;
    private boolean ativo;

    @OneToMany(mappedBy = "curso", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Aula> aulas = new ArrayList<>();

    public Curso () {

    }

    public Curso (String nome, String descricao, boolean ativo){
        this.nome = nome;
        this.descricao = descricao;
        this.ativo = ativo;
    }
}
