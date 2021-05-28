package br.com.eleicao.api.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eleicao.api.domain.Eleicao;
import br.com.eleicao.api.repository.EleicaoRepository;

@Service
public class EleicaoService {
    
    @Autowired
    private EleicaoRepository eleicaoRepository;

    public Eleicao salvar(Eleicao eleicao) {
   
        return eleicaoRepository.salvar(eleicao);
    }

    public Eleicao pegarPorId(Long id) {
        return eleicaoRepository.pesquisaPorId(id);
    }

    @Transactional
    public Eleicao update(Long id, Eleicao eleicao) {
        eleicaoRepository.update(eleicao);
        return eleicaoRepository.pesquisaPorId(id);
    }

    public void delete(Long id) {
        eleicaoRepository.delete(id);
    }

	public ArrayList<Eleicao> listarEleicoes() {
		
    	ArrayList<Eleicao> eleicoes = eleicaoRepository.listarEleicoes();
    	
		return eleicoes;
	}

}
