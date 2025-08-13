package com.marcos.loltracker.loltracker.Repository;

import com.marcos.loltracker.loltracker.DTO.CampeaoHabilidadesDTO;
import com.marcos.loltracker.loltracker.Model.Campeao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CampeaoRepository extends JpaRepository<Campeao, Long> {
   CampeaoHabilidadesDTO findByNomeIgnoreCase(String nome);

   boolean existsByNomeIgnoreCase(String nome);

   List<Campeao> findByLane(String lane);
}
