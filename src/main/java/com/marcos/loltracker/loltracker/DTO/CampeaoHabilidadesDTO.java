package com.marcos.loltracker.loltracker.DTO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CampeaoHabilidadesDTO {
    private String nome;

    private String lane;

    private String role;

    private int maestria;

    private String campeao;

    private Map<String, HabilidadeDTO> habilidades = new HashMap<>();

    private HabilidadeDTO q;

    private HabilidadeDTO w;

    private HabilidadeDTO e;

    private HabilidadeDTO r;

    private HabilidadeDTO passive;

    public String getCampeao() {
        return campeao;
    }

    public void setCampeao(String campeao) {
        this.campeao = campeao;
    }

    public HabilidadeDTO getQ() {
        return q;
    }

    public void setQ(HabilidadeDTO q) {
        this.q = q;
    }

    public HabilidadeDTO getPassive() {
        return passive;
    }

    public void setPassive(HabilidadeDTO passive) {
        this.passive = passive;
    }

    public HabilidadeDTO getR() {
        return r;
    }

    public void setR(HabilidadeDTO r) {
        this.r = r;
    }

    public HabilidadeDTO getE() {
        return e;
    }

    public void setE(HabilidadeDTO e) {
        this.e = e;
    }

    public HabilidadeDTO getW() {
        return w;
    }

    public void setW(HabilidadeDTO w) {
        this.w = w;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getMaestria() {
        return maestria;
    }

    public void setMaestria(int maestria) {
        this.maestria = maestria;
    }

    public Map<String, HabilidadeDTO> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<HabilidadeDTO> habilidadesList) {
        for (HabilidadeDTO habilidade : habilidadesList) {
            this.habilidades.put(habilidade.getTipo(), habilidade);
/* <<<<<<<<<<<<<<  ✨ Windsurf Command ⭐ >>>>>>>>>>>>>>>> */
    /**
     * Sets the habilidades of this object using a list of {@link HabilidadeDTO}s.
     *
     * @param habilidadesList a list of {@link HabilidadeDTO}s to set
     */
/* <<<<<<<<<<  e4d2e082-73dd-4bb7-9779-32605d4b0403  >>>>>>>>>>> */
        }
    }

    public void setHabilidades(Map<String, HabilidadeDTO> habilidades) {
        this.habilidades = habilidades;
    }

    public String iconeMaestria() {
        return "/maestria/maestria" + maestria + ".png";
    }

}
