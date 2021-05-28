package br.com.eleicao.api.rest;

import java.awt.List;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eleicao.api.domain.Eleicao;
import br.com.eleicao.api.service.EleicaoService;

@RestController
@RequestMapping("/api")
public class EleicaoResource {
    
    @Autowired
    private EleicaoService eleicaoService;
    
    @GetMapping("/eleicoes")
    public ResponseEntity<ArrayList<Eleicao>> listarEleicoes() throws URISyntaxException {
        
    	ArrayList<Eleicao> eleicoes = eleicaoService.listarEleicoes();
        
        return ResponseEntity.ok().body(eleicoes);
    } 

    @PostMapping("/eleicoes")
    public ResponseEntity<Eleicao> criarEleicao(@RequestBody Eleicao eleicao) throws URISyntaxException {
               
       Eleicao eleicaoCriada = eleicaoService.salvar(eleicao);
        
       return ResponseEntity.created(new URI("/api/eleicoes")).body(eleicaoCriada);
    }
    
    @GetMapping("/eleicoes/{id}")
    public ResponseEntity<Eleicao> pegarEleicaoPorId(@PathVariable Long id) throws URISyntaxException {
                 
        Eleicao eleicao = eleicaoService.pegarPorId(id);
        
        return ResponseEntity.ok().body(eleicao);
    } 
    
    @PutMapping("/eleicoes/{id}")
    public ResponseEntity<Eleicao> atualizarEleicao(@PathVariable Long id, @RequestBody Eleicao eleicao) throws URISyntaxException {
                 
        Eleicao eleicaoUpdated = eleicaoService.update(id, eleicao);
        
        return ResponseEntity.ok().body(eleicaoUpdated);
    } 
    
    @DeleteMapping("/eleicoes/{id}")
    public void deleteEleicao(@PathVariable Long id) throws URISyntaxException {
        eleicaoService.delete(id);
    }
}
