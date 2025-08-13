package com.marcos.loltracker.loltracker.Service;

import com.marcos.loltracker.loltracker.DTO.CampeaoDTO;
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
}