package com.maxi.venda.entities;

import jakarta.persistence.Entity;

@Entity
public class Pessoa extends BaseEntity {
    private String nome;

    public Pessoa() {
        super();
    }

    public Pessoa(String nome) {
        this();
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}