package br.com.eleicao.api.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.eleicao.api.domain.Eleicao;
import br.com.eleicao.api.mapper.EleicaoMapper;

@Repository
public class EleicaoRepository {

    @Autowired
    private EleicaoMapper eleicaoMapper;
    
    public Eleicao salvar(Eleicao eleicao) {
        eleicaoMapper.insert(eleicao);
        
        return eleicao;
        
    }

    public Eleicao pesquisaPorId(Long id) {
        return eleicaoMapper.selectById(id);
    }

    public Eleicao update(Eleicao eleicao) {
        eleicaoMapper.update(eleicao);
        return eleicaoMapper.selectById(eleicao.getId());
    }

    public void delete(Long id) {
        eleicaoMapper.delete(id);
        
    }

}
