package br.com.eleicao.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eleicao.api.domain.Pessoa;
import br.com.eleicao.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
    private PessoaRepository pessoaRepository;
	
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.salvar(pessoa);
	}

}
