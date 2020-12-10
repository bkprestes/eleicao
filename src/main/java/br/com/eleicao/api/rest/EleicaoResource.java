package br.com.eleicao.api.rest;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eleicao.api.domain.Eleicao;
import br.com.eleicao.api.repository.EleicaoRepository;
import br.com.eleicao.api.service.EleicaoService;

@RestController
@RequestMapping("/api")
public class EleicaoResource {
    
    @Autowired
    private EleicaoService eleicaoService;

    @PostMapping("/eleicoes")
    public ResponseEntity<Eleicao> criarEleicao(@RequestBody Eleicao eleicao) throws URISyntaxException {
       
       Eleicao eleicaoCriada = eleicaoService.salvar(eleicao);
        
       return ResponseEntity.created(new URI("/api/eleicoes")).body(eleicaoCriada);
    }
    
}
