package br.com.eleicao.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.eleicao.api.domain.Pessoa;
import br.com.eleicao.api.mapper.PessoaMapper;

@Repository
public class PessoaRepository {

	@Autowired
    private PessoaMapper pessoaMapper;
    
    public Pessoa salvar(Pessoa pessoa) {
    	pessoaMapper.insert(pessoa);
        
        return pessoa;
        
    }

	public Pessoa pesquisaPorId(Long id) {
		return pessoaMapper.selectById(id);
	}

	public Pessoa update(Pessoa pessoa) {
		pessoaMapper.update(pessoa);
        return pessoaMapper.selectById(pessoa.getId());
		
	}

	public void delete(Long id) {
		pessoaMapper.delete(id);
		
	}
}
