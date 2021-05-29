package br.com.eleicao.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PessoaEdition {
	
    @NotBlank
    @NotNull
	private String nome;
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
}
