package com.marcos.loltracker.loltracker.Repository;

import com.marcos.loltracker.loltracker.Model.Campeao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CampeaoRepository extends JpaRepository<Campeao, Long> {
   Optional<Campeao> findByNomeIgnoreCase(String nome);

   boolean existsByNomeIgnoreCase(String nome);

   List<Campeao> findByLane(String lane);
}
