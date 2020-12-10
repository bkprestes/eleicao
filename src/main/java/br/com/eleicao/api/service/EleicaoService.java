package br.com.eleicao.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eleicao.api.domain.Eleicao;
import br.com.eleicao.api.repository.EleicaoRepository;

@Service
public class EleicaoService {
    
    @Autowired
    private EleicaoRepository eleicaoRepository;

    public Eleicao salvar(Eleicao eleicao) {
   
        return eleicaoRepository.salvar(eleicao);
    }

}
