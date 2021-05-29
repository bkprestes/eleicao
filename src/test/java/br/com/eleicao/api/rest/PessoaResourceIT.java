package br.com.eleicao.api.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import br.com.eleicao.api.dto.PessoaEdition;
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
    	pessoa.setId(100004L);
    	pessoa.setNome("Maria Fatima");
    	pessoa.setCpf("111.111.222-00");
        
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
    
    @Transactional
    @Test
    void get_byId_ok() throws Exception {
        Long idPessoa = 100001L;
        
        MockHttpServletResponse response = mockMvc.perform(get("/api/pessoa/" + idPessoa)).andExpect(status().isOk()).andReturn().getResponse();
        Pessoa pessoaFound = JsonHelper.toObject(response.getContentAsByteArray(), Pessoa.class);
        
        assertEquals(idPessoa, pessoaFound.getId());
        assertEquals("Pablo Ricardo", pessoaFound.getNome());
        assertEquals("123.456.789-00", pessoaFound.getCpf());
    }
    
    @Transactional
    @Test
    void update_pessoaById_statusOk() throws Exception {
        Long idPessoa = 100001L;
        Pessoa pessoaBefore = pessoaRepository.pesquisaPorId(idPessoa);
        
        PessoaEdition pessoaAfter = new PessoaEdition();
        pessoaAfter.setNome("Rose");
        
        MockHttpServletResponse response = mockMvc.perform(put("/api/pessoa/" + idPessoa)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonHelper.toJson(pessoaAfter))).andExpect(status().isOk()).andReturn().getResponse();
        Pessoa pessoaFound = JsonHelper.toObject(response.getContentAsByteArray(), Pessoa.class);
        
        assertNotEquals(pessoaFound.getNome(), pessoaBefore.getNome());
        assertEquals(pessoaFound.getNome(), "Rose");
        assertEquals(pessoaFound.getCpf(), "123.456.789-00");
    }
    
    @Transactional
    @Test
    void delete_pessoaById_statusOk() throws Exception {
        Long idPessoa = 100003L;
        
        mockMvc.perform(delete("/api/pessoa/" + idPessoa)).andExpect(status().isOk());
        
        Pessoa pessoaAfter = pessoaRepository.pesquisaPorId(idPessoa);
        assertNull(pessoaAfter);
        
    }
}
