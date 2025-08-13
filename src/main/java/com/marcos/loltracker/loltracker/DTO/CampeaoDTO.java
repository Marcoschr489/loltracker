package com.marcos.loltracker.loltracker.DTO;

import java.util.List;

public class CampeaoDTO {
    private String role;
    private String nome;
    private String lane;
    private int maestria;
    private List<HabilidadeDTO> habilidades;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLane() {
        return lane;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public int getMaestria() {
        return maestria;
    }

    public void setMaestria(int maestria) {
        this.maestria = maestria;
    }

    public List<HabilidadeDTO> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<HabilidadeDTO> habilidades) {
        this.habilidades = habilidades;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
