package br.com.eleicao.api.rest;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.eleicao.api.domain.Pessoa;
import br.com.eleicao.api.service.PessoaService;

@RestController
@RequestMapping("/api")
public class PessoaResource {

	@Autowired
	private PessoaService pessoaService;

	@PostMapping("/pessoa")
    public ResponseEntity<Pessoa> criarPessoa(@RequestBody Pessoa pessoa) throws URISyntaxException {
               
		Pessoa pessoaCriada = pessoaService.salvar(pessoa);
        return ResponseEntity.created(new URI("/api/pessoa")).body(pessoaCriada);
    }
	
	@GetMapping("/pessoa/{id}")
    public ResponseEntity<Pessoa> pegarPessoaPorId(@PathVariable Long id) throws URISyntaxException {
                 
		Pessoa pessoa = pessoaService.pegarPorId(id);
        return ResponseEntity.ok().body(pessoa);
    } 
}
