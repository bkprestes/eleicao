package br.com.eleicao.api.mapper;

import org.apache.ibatis.annotations.Mapper;

import br.com.eleicao.api.domain.Eleicao;

@Mapper
public interface EleicaoMapper {

    void insert(Eleicao eleicao);

    Eleicao selectById(Long id);

    void update(Eleicao eleicao);

}
