package br.com.eleicao.api.domain;

import java.time.LocalDate;

public class Eleicao {

    private Long id;
    private String nome;
    private LocalDate data;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public LocalDate getData() {
        return data;
    }
    public void setData(LocalDate data) {
        this.data = data;
    }    
}
