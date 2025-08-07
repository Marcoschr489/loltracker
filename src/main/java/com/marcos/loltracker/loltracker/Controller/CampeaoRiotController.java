package com.marcos.loltracker.loltracker.Controller;

import com.marcos.loltracker.loltracker.DTO.ChampionDTO;
import com.marcos.loltracker.loltracker.Model.Campeao;
import com.marcos.loltracker.loltracker.Service.RiotApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@RestController
@RequestMapping("/api/campeoes")
public class CampeaoRiotController {

    private final RiotApiService riotApiService;

    private final String API_KEY = "RGAPI-70636ff0-4d49-471b-8329-5b70277a827c";
    private final String BASE_URL = "https://br1.api.riotgames.com/lol/summoner/v4/summoners/by-name/";

    public CampeaoRiotController(RiotApiService riotApiService) {
        this.riotApiService = riotApiService;
    }

    @GetMapping("/riot/summoner/{nickname}")
    public ResponseEntity<String> getSummonerinfo(@PathVariable String nickname) {
        String url = BASE_URL + nickname + "?api_key=" + API_KEY;

        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);

        return ResponseEntity.ok(result);
    }

    @GetMapping("/riot")
    public List<ChampionDTO> listarCampeoesRiot() {
        return riotApiService.getChampions();
    }

    @PostMapping("/riots")
    public ResponseEntity<List<Campeao>> importarCampeoes() {
        System.out.println(">>> INÍCIO importarCampeoes ()");
        List<Campeao> campeoes = riotApiService.importarCampeoesDaRiot();
        System.out.println(">>> FIM importarCampeoes ()");
        return ResponseEntity.ok(campeoes);
    }
}
