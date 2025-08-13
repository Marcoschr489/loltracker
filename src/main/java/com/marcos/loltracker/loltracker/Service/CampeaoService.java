package com.marcos.loltracker.loltracker.Service;
import com.marcos.loltracker.loltracker.DTO.CampeaoDTO;
import com.marcos.loltracker.loltracker.DTO.CampeaoHabilidadesDTO;
import com.marcos.loltracker.loltracker.DTO.HabilidadeDTO;
import com.marcos.loltracker.loltracker.Model.Campeao;
import com.marcos.loltracker.loltracker.Model.Habilidade;
import com.marcos.loltracker.loltracker.Repository.CampeaoRepository;
import com.marcos.loltracker.loltracker.Repository.HabilidadeRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CampeaoService {

    private final CampeaoRepository campeaoRepository;
    private final HabilidadeRepository habilidadeRepository;

    public CampeaoService(CampeaoRepository campeaoRepository, HabilidadeRepository habilidadeRepository) {
        this.habilidadeRepository = habilidadeRepository;
        this.campeaoRepository = campeaoRepository;
    }

    //busca todos os campeões
    public List<Campeao> listarTodos() {
        return campeaoRepository.findAll();
    }

    //busca por ID para edição/exclusão
    public Campeao buscarPorId(Long id) {
        return campeaoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Campeão não encontrado"));
    }

    //exclui campeão por ID
    public void excluir(Long id) {
        campeaoRepository.deleteById(id);
    }

    //busca por nome
    public CampeaoHabilidadesDTO buscarPorNome(String nome) {
        return campeaoRepository.findByNomeIgnoreCase(nome);
    }

    public List<Habilidade> lerHabilidadesDeCampeao(String nomeCampeao) {
        List<Habilidade> habilidades = new ArrayList<>();
        String nomeCapitalizado = capitalize(nomeCampeao);
        String baseDir = "src/main/resources/descricao/" + nomeCapitalizado + "/";

        String[] tipos = {"Passive", "Q", "W", "E", "R"};

        for (String tipo : tipos) {
            String arquivoPath = baseDir + nomeCapitalizado + "_" + tipo + ".txt";
            try {
                Path path = Paths.get(arquivoPath);
                if (Files.exists(path)) {
                    String linha = Files.readString(path).trim();
                    String[] partes = linha.split("\",\\s*\"");
                    for (int i = 0; i < partes.length; i++) {
                        partes[i] = partes[i].replaceAll("^\"|\"$", "");
                    }

                    if (partes.length == 5) {
                        String tipoHabilidade = partes[0];
                        String nome = partes[1];
                        String efeito = partes[2];
                        String recurso = partes[3];
                        String custo = partes[4];

                        habilidades.add(new Habilidade(tipoHabilidade, nome, efeito, recurso, custo));
                    } else
                        habilidades.add(new Habilidade(tipo, "Desconhecida", "Arquivo não encontrado.", "?", "?"));
                }
            } catch (Exception e) {
                habilidades.add(new Habilidade(tipo, "Erro", "Erro ao ler arquivo.", "?", "?"));
                e.printStackTrace();
            }
        }

        return habilidades;
    }

    private String capitalize(String nome) {
        return nome.substring(0, 1).toUpperCase() + nome.substring(1).toLowerCase();
    }

    public CampeaoHabilidadesDTO carregarCampeao(String nome) {
        return null;
    }

    public void salvar(CampeaoHabilidadesDTO dto) {
        Campeao campeao = new Campeao();
        campeao.setNome(dto.getNome());
        campeao.setLane(dto.getLane());
        campeao.setRole(dto.getRole());
        campeao.setMaestria(dto.getMaestria());
        campeaoRepository.save(campeao);

        if (dto.getHabilidades() != null) {
            for (Map.Entry<String, HabilidadeDTO> entry : dto.getHabilidades().entrySet()) {
                HabilidadeDTO habilidadeDTO = entry.getValue();
                Habilidade habilidade = new Habilidade();
                habilidade.setTipo(habilidadeDTO.getTipo());
                habilidade.setNome(habilidadeDTO.getNome());
                habilidade.setEfeito(habilidadeDTO.getEfeito());
                habilidade.setRecurso(habilidadeDTO.getRecurso());
                habilidade.setCusto(habilidadeDTO.getCusto());
                habilidade.setCampeao(campeao);

                habilidadeRepository.save(habilidade);
            }
        }
    }

    public void salvarOuAtualizarCampeao(Campeao campeao) {
        campeaoRepository.save(campeao);
    }

    public Map<String, String> carregarTooltipsDoCampeao(String nomeCampeao) {
        Map<String, String> tooltips = new HashMap<>();
        String[] habilidades = {"Q", "W", "E", "R", "Passive"};

        for (String habilidade : habilidades) {
            String caminho = "src/main/resources/static/splashArt/" + nomeCampeao.toLowerCase() + "/" + habilidade.toUpperCase() + "tooltip.txt";
            try {
                List<String> linhas = Files.readAllLines(Paths.get(caminho));
                if (!linhas.isEmpty()) {
                    tooltips.put(habilidade, linhas.stream().collect(Collectors.joining(" ")));
                }
            } catch (IOException e) {
                tooltips.put(habilidade, "Descrição não encontrada");
            }
        }

        return tooltips;
    }

    private static final Map<String, String> videoMap = Map.ofEntries(
            Map.entry("rengar", "gbHGFazlpCA"),
            Map.entry("shaco", "Q9m48jiVWL4"),
            Map.entry("vi", "vp3ZrRJz-ZY"),
            Map.entry("zed", "2DFtlNGzMIA"),
            Map.entry("twitch", "cFtH_wFJBLs"),
            Map.entry("kayn", "pPaauwI6G5s"),
            Map.entry("briar", "2QI2pfum47U"),
            Map.entry("brand", "lwMYwlcVHbQ"),
            Map.entry("mordekaiser", "GcR1_McnJbU"),
            Map.entry("fiddlesticks", "S6r5TwgDL4Q"),
            Map.entry("nunuWillump", "udTIlmIa-R4"),
            Map.entry("pantheon", "DTNwrJGjpxo"),
            Map.entry("nocturne", "Jd4jQOdHFo4")
    );


    public String getVideoIdForCampeao(String nome) {
        return CampeaoService.videoMap.getOrDefault(nome.toLowerCase(), "defaultVideoId");
    }

    public String getCaminhoIconeMaestria(int maestria) {
        return "/maestria/maestria" + maestria + ".png";
    }

    private List<CampeaoDTO> campeoes = new ArrayList<>();
    public void salvar(CampeaoDTO campeao) {
        campeoes.add(campeao);
    }

    public CampeaoDTO buscarPorNomes(String nome) {
        for (CampeaoDTO c : campeoes) {
            if (c.getNome().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }
}



