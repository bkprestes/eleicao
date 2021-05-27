package br.com.eleicao.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eleicao.api.domain.Pessoa;
import br.com.eleicao.api.repository.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
    private PessoaRepository pessoaRepository;
	
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.salvar(pessoa);
	}

	public Pessoa pegarPorId(Long id) {
		return pessoaRepository.pesquisaPorId(id);
	}

	@Transactional
	public Pessoa update(Long id, Pessoa pessoa) {
		pessoaRepository.update(pessoa);
        return pessoaRepository.pesquisaPorId(id);
	}

	public void delete(Long id) {
		pessoaRepository.delete(id);		
	}

}
