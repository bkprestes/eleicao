package br.com.eleicao.api.mapper;

import org.apache.ibatis.annotations.Mapper;

import br.com.eleicao.api.domain.Pessoa;

@Mapper
public interface PessoaMapper {
	
	public void insert(Pessoa pessoa);

	public Pessoa selectById(Long id);

	public void update(Pessoa pessoa);
}
