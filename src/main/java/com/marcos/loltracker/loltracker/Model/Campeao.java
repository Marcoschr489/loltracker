package com.marcos.loltracker.loltracker.Model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Campeao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String videoId;

    private String nome;

    private String lane;

    private String role;

    private int maestria;

    private String splashArtPath;

    @OneToMany(mappedBy = "campeao", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Habilidade> habilidades = new ArrayList<>();

    public void addHabilidade(Habilidade habilidade) {
        habilidade.setCampeao(this);
        habilidades.add(habilidade);
    }

    public void removerHabilidade(Habilidade habilidade) {
        habilidade.setCampeao(null);
        habilidades.remove(habilidade);
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getMaestria() {
        return maestria;
    }

    public void setMaestria(int nivelMaestria) {
        this.maestria = nivelMaestria;
    }

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

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Habilidade> getHabilidades() {
        return habilidades;
    }

    public void setHabilidades(List<Habilidade> habilidades) {
        this.habilidades = habilidades;
    }

    public String getSplashArtPath() {
        return splashArtPath;
    }

    public void setSplashArtPath(String splashArtPath) {
        this.splashArtPath = splashArtPath;
    }



}
