package com.marcos.loltracker.loltracker.Service;

import com.marcos.loltracker.loltracker.DTO.ChampionDTO;
import com.marcos.loltracker.loltracker.Model.Campeao;
import com.marcos.loltracker.loltracker.Repository.CampeaoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;


@Service
public class RiotApiService {

    private static final String CHAMPION_LIST_URL = "https://ddragon.leagueoflegends.com/cdn/14.15.1/data/pt_BR/champion.json";

    private final RestTemplate restTemplate;
    private final CampeaoRepository campeaoRepository;

    public RiotApiService(RestTemplate restTemplate, CampeaoRepository campeaoRepository) {
        this.restTemplate = restTemplate;
        this.campeaoRepository = campeaoRepository;
    }

    public List<Campeao> importarCampeoesDaRiot() {
        Map<String, Object> response = restTemplate.getForObject(CHAMPION_LIST_URL, Map.class);
        Map<String, Object> data = (Map<String, Object>) response.get("data");

        List<Campeao> campeoesSalvos = new ArrayList<>();
        for (Object value : data.values()) {
            Map<String, Object> champMap = (Map<String, Object>) value;
            String nome = (String) champMap.get("name");

            if (campeaoRepository.existsByNomeIgnoreCase(nome)) {
                continue; // Campeão já existente, pular para o próximo
            }

            Campeao novo = new Campeao();
            novo.setNome(nome);
            novo.setMaestria(0);
            novo.setVideoId(null);

            String splashArt = "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/" + champMap.get("id") + "_0.jpg";
            novo.setSplashArtPath(splashArt);

            novo.setLane("Desconhecida");
            novo.setRole("Desconhecida");

            campeaoRepository.save(novo);
            campeoesSalvos.add(novo);
        }

        return campeoesSalvos;
    }

    public List<ChampionDTO> getChampions() {
        try {
            Map<String, Object> response = restTemplate.getForObject(CHAMPION_LIST_URL, Map.class);
            Map<String, Object> data = (Map<String, Object>) response.get("data");

            List<ChampionDTO> champions = new ArrayList<>();
            for (Object value : data.values()) {
                Map<String, Object> champMap = (Map<String, Object>) value;

                ChampionDTO dto = new ChampionDTO();
                dto.setId((String) champMap.get("id"));
                dto.setName((String) champMap.get("name"));
                dto.setTitle((String) champMap.get("title"));
                dto.setBlurb((String) champMap.get("blurb"));

                champions.add(dto);
            }

            return champions;
        } catch (Exception e) {
            System.out.println("Erro ao buscar Campeões:" + e.getMessage());
            return Collections.emptyList();
        }
    }
}