package com.marcos.loltracker.loltracker.Controller;

import com.marcos.loltracker.loltracker.DTO.CampeaoDTO;
import com.marcos.loltracker.loltracker.DTO.CampeaoHabilidadesDTO;
import com.marcos.loltracker.loltracker.DTO.HabilidadeDTO;
import com.marcos.loltracker.loltracker.Model.Campeao;
import com.marcos.loltracker.loltracker.Model.Habilidade;
import com.marcos.loltracker.loltracker.Repository.CampeaoRepository;
import com.marcos.loltracker.loltracker.Service.CampeaoService;
import com.marcos.loltracker.loltracker.Service.HabilidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/campeoes")
public class CampeaoController {

    @Autowired
    private CampeaoService campeaoService;

    @Autowired
    private HabilidadeService habilidadeService;

    @Autowired
    private CampeaoRepository campeaoRepository;

    // ✅ Página de cadastro de campeão (formulário)
    @GetMapping("/novo")
    public String novo(Model model) {
        model.addAttribute("campeao", new Campeao());
        return "novo";
    }

    // ✅ Cadastro ou edição de campeão
    @PostMapping("/salvar")
    public String salvarCampeao(@ModelAttribute Campeao campeao) {
        String videoId = campeaoService.getVideoIdForCampeao(campeao.getNome());
        campeaoRepository.save(campeao);
        return "redirect:/campeoes/lista";
    }

    // ✅ Página que lista todos os campeões cadastrados
    @GetMapping("/campeoes/lista")
    public String listarTodos(Model model) {
        List<Campeao> campeoes = campeaoRepository.findAll();
        Map<String, Map<String, String>> tooltips = habilidadeService.carregarTooltips(campeoes);

        model.addAttribute("tooltips", tooltips);
        model.addAttribute("campeoes", campeoes);

        return "lista";
    }

    // ✅ Edição de um campeão existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Optional<Campeao> campeao = campeaoRepository.findById(id);
        if (campeao.isPresent()) {
            model.addAttribute("campeao", campeao.get());
            return "novo";
        } else {
            return "redirect:/campeoes";
        }
    }

    // ✅ Exclusão de um campeão
    @GetMapping("/excluir/{id}")
    public String excluirCampeao(@PathVariable Long id) {
        campeaoService.excluir(id);
        return "redirect:/campeoes/lista";
    }

    // ✅ Endpoint que retorna o nome da splash art com base no nome do campeão
    @GetMapping("/{nome}/splash")
    @ResponseBody
    public Map<String, String> getSplashArt(@PathVariable String nome) {
        Map<String, String> response = new HashMap<>();
        response.put("splashArt", nome.toLowerCase()); // apenas padronização
        return response;
    }

    // ✅ Endpoint que retorna todas as habilidades com base no nome do campeão
    @GetMapping("/descricao/habilidades/{nome}")
    @ResponseBody
    public ResponseEntity<Map<String, HabilidadeDTO>> getHabilidadess(@PathVariable String nome) {
        Map<String, HabilidadeDTO> habilidades = new HashMap<>();
        for (String tipo : List.of("Passive", "Q", "W", "E", "R")) {
            habilidades.put(tipo, habilidadeService.carregarHabilidade(nome.toLowerCase(), tipo));
        }
        return ResponseEntity.ok(habilidades);
    }

    @GetMapping("/habilidades/campeao/{nomeCampeao}")
    public ResponseEntity<Map<String, Habilidade>> getHabilidades(@PathVariable String nomeCampeao) {
        Map<String, Habilidade> habilidades = habilidadeService.carregarHabilidades(nomeCampeao.toLowerCase());
        if (habilidades.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(habilidades);

    }

    @GetMapping("/")
    public String redirecionarParaLista() {
        return "redirect:/campeoes/lista";
    }

    @GetMapping("/lista")
    public String listarCampeoes(Model model) {
        List<Campeao> campeoes = campeaoRepository.findAll();

        Map<String, Map<String, String>> todosTooltips = new HashMap<>();
        for (Campeao campeao : campeoes) {
            Map<String, String> tooltips = campeaoService.carregarTooltipsDoCampeao(campeao.getNome().toLowerCase());
            todosTooltips.put(campeao.getNome().toLowerCase(), tooltips);
        }
        model.addAttribute("campeoes", campeoes);
        model.addAttribute("tooltips", todosTooltips);
        return "lista";
    }

    @GetMapping("/campeao/novo")
    public String novoCampeao(Model model) {
        model.addAttribute("campeao", new Campeao());
        return "novo";
    }

    @GetMapping("/campeao/{nome}")
    @ResponseBody
    public ResponseEntity<CampeaoDTO> buscarPorNome(@PathVariable String nome) {
        CampeaoDTO dto = campeaoService.buscarPorNomes(nome);
        if (dto == null) {
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}