package com.desafio.projeto_final.repository;


import com.desafio.projeto_final.model.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Long> {

    List<Aula> findByCursoId(Long cursoId);
}
