package com.example.algamoneyapi.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long codigo;

    @NotNull
    String nome;

    @Embedded
    Endereco endereco;

    @NotNull
    Boolean ativo;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public boolean isInativo(){
        return !this.ativo;
    }
}
