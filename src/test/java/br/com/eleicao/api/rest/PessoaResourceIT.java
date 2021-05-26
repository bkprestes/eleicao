package br.com.eleicao.api.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import br.com.eleicao.api.domain.Pessoa;
import br.com.eleicao.api.helper.JsonHelper;
import br.com.eleicao.api.repository.PessoaRepository;

@SpringBootTest
@AutoConfigureMockMvc
public class PessoaResourceIT {

	@Autowired
    MockMvc mockMvc;
    
    @Autowired
    PessoaRepository pessoaRepository;
    
    @Transactional
    @Test
    void create_pessoa_StatusCreated() throws Exception {
    	Pessoa pessoa = new Pessoa();
    	pessoa.setId(100001L);
    	pessoa.setNome("Rebeca Andrade");
    	pessoa.setCpf("123456789-00");
        
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/pessoa")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonHelper.toJson(pessoa))
                ).andExpect(status().isCreated()).andReturn().getResponse();
        
        Pessoa pessoaCriada = JsonHelper.toObject(response.getContentAsByteArray(), Pessoa.class);
        assertNotNull(pessoaCriada.getId());
        
        Pessoa pessoaPersistida = pessoaRepository.pesquisaPorId(pessoaCriada.getId());
        assertEquals(pessoa.getNome(), pessoaPersistida.getNome());
        assertEquals(pessoa.getCpf(), pessoaPersistida.getCpf());
    }
}
