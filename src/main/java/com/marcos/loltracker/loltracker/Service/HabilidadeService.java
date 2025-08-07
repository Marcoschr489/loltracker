package com.marcos.loltracker.loltracker.Service;

import com.marcos.loltracker.loltracker.DTO.HabilidadeDTO;
import com.marcos.loltracker.loltracker.Model.Campeao;
import com.marcos.loltracker.loltracker.Model.Habilidade;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HabilidadeService {

    public Map<String, Habilidade> carregarHabilidades(String nomeCampeao) {
        Map<String, Habilidade> habilidades = new HashMap<>();
        String[] tipos = {"Passive", "Q", "W", "E", "R"};

        for (String tipo : tipos) {
            try {
                Path path = Paths.get("src/main/resources/descricao/habilidades/" +
                        nomeCampeao + "/" + tipo + ".txt");
                List<String> linhas = Files.readAllLines(path);

                if (linhas.size() >= 5) {
                    Habilidade habilidade = new Habilidade();
                    habilidade.setTipo(linhas.get(0).replace("\"", ""));
                    habilidade.setNome(linhas.get(1).replace("\"", ""));
                    habilidade.setEfeito(linhas.get(2).replace("\"", ""));
                    habilidade.setRecurso(linhas.get(3).replace("\"", ""));
                    habilidade.setCusto(linhas.get(4).replace("\"", ""));
                    habilidades.put(tipo, habilidade);
                }
            } catch (IOException e) {
                System.out.println("Erro ao ler habilidade" + tipo + " do campeão " + nomeCampeao);
            }
        }
        return habilidades;
    }

    public HabilidadeDTO carregarHabilidade(String nome, String tipo) {
        String caminho = "src/main/resources/descricao/habilidades/" + nome + "/" + tipo + ".txt";
        HabilidadeDTO habilidade = new HabilidadeDTO();
        habilidade.setTipo(tipo);

        try {
            List<String> linhas = Files.readAllLines(Paths.get(caminho));
            if (linhas.size() >= 5) {
                habilidade.setNome(linhas.get(1).trim());
                habilidade.setEfeito(linhas.get(2).trim());
                habilidade.setRecurso(linhas.get(3).trim());
                habilidade.setCusto(linhas.get(4).trim());
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler " + tipo + " do campeão " + nome + ": " + e.getMessage());
        }
        return habilidade;
    }

    public Map<String, Map<String, String>> carregarTooltips(List<Campeao> campeoes) {
        Map<String, Map<String, String>> todosTooltips = new HashMap<>();

        for (Campeao campeao : campeoes) {
            String nome = campeao.getNome().toLowerCase().replaceAll("\\s+", "");
            Map<String, String> tooltips = new HashMap<>();

            for (String habilidade : List.of("q", "w", "e", "r", "passive")) {
                String caminho = "descricao/habilidades/" + nome + "/" + habilidade.toUpperCase() + "tooltip.txt";
                try (InputStream input = getClass().getClassLoader().getResourceAsStream(caminho)) {
                    if (input != null) {
                        List<String> linhas = new BufferedReader(new InputStreamReader(input)).lines().toList();
                        if (linhas.size() >= 3) {
                            String resumo = linhas.get(2);
                            tooltips.put(habilidade, resumo);
                        }
                    }

                }catch(IOException e) {
                        e.printStackTrace();
                    }
                }

                todosTooltips.put(nome, tooltips);
            }

            return todosTooltips;
    }
}