package br.com.eleicao.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eleicao.api.domain.Pessoa;
import br.com.eleicao.api.dto.PessoaEdition;
import br.com.eleicao.api.dto.mapper.PessoaDtoMapper;
import br.com.eleicao.api.repository.PessoaRepository;

@Service
@Transactional
public class PessoaService {

	@Autowired
    private PessoaRepository pessoaRepository;
	
	@Autowired
	private PessoaDtoMapper pessoaDtoMapper;
	
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.salvar(pessoa);
	}

	public Pessoa pegarPorId(Long id) {
		return pessoaRepository.pesquisaPorId(id);
	}

	@Transactional
	public Pessoa update(Long id, PessoaEdition pessoaEdition) {
	    Pessoa pessoa = pessoaDtoMapper.editionToDomain(pessoaEdition);
	    pessoa.setId(id);
	    this.pessoaRepository.update(pessoa);
        return this.pegarPorId(id);
	}

	public void delete(Long id) {
		pessoaRepository.delete(id);		
	}

}
 