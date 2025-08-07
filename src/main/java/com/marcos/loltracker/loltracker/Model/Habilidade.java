package com.marcos.loltracker.loltracker.Model;

import jakarta.persistence.*;


@Entity
public class  Habilidade {
    @Id
    @GeneratedValue
    private Long id;
    private String tipo;
    
    @ManyToOne
    @JoinColumn(name = "campeao_id")
    private Campeao campeao;
    
    private String nome;

    private String efeito;

    private String recurso;

    private String custo;

    public Habilidade(String tipo, String nome, String efeito, String recurso, String custo) {
    }

    public Habilidade() {

    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getCusto() {
        return custo;
    }

    public void setCusto(String custo) {
        this.custo = custo;
    }

    public String getRecurso() {
        return recurso;
    }

    public void setRecurso(String recurso) {
        this.recurso = recurso;
    }

    public String getEfeito() {
        return efeito;
    }

    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCampeao(Campeao campeao) {
        this.campeao = campeao;
    }

    public Campeao getCampeao() {
        return campeao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
