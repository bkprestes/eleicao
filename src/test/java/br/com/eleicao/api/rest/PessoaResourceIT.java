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

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import br.com.eleicao.api.domain.Eleicao;
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
    
    @Transactional
    @Test
    void get_byId_ok() throws Exception {
        Long idPessoa = 100001L;
        Pessoa pessoa = new Pessoa();
        pessoa.setId(idPessoa);
        pessoa.setNome("uma eleicao");
        pessoa.setCpf("123.456.789-00");
        pessoaRepository.salvar(pessoa);
        
        MockHttpServletResponse response = mockMvc.perform(get("/api/pessoa/" + idPessoa)).andExpect(status().isOk()).andReturn().getResponse();
        Pessoa pessoaFound = JsonHelper.toObject(response.getContentAsByteArray(), Pessoa.class);
        
        assertEquals(pessoa.getId(), pessoaFound.getId());
        assertEquals(pessoa.getNome(), pessoaFound.getNome());
        assertEquals(pessoa.getCpf(), pessoaFound.getCpf());
    }
    
    @Transactional
    @Test
    void update_pessoaById_statusOk() throws Exception {
        Long idPessoa = 100001L;
        Pessoa pessoaBefore = new Pessoa();
        pessoaBefore.setId(idPessoa);
        pessoaBefore.setNome("Pablo");
        pessoaBefore.setCpf("123.456.789-00");
        pessoaRepository.salvar(pessoaBefore);
        
        Pessoa pessoaAfter = pessoaRepository.pesquisaPorId(idPessoa);
        pessoaAfter.setNome("Pablo Ricardo");
        
        MockHttpServletResponse response = mockMvc.perform(put("/api/pessoa/" + idPessoa)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonHelper.toJson(pessoaAfter))).andExpect(status().isOk()).andReturn().getResponse();
        Pessoa pessoaFound = JsonHelper.toObject(response.getContentAsByteArray(), Pessoa.class);
        
        assertNotEquals(pessoaFound.getNome(), pessoaBefore.getNome());
        assertEquals(pessoaFound.getNome(), "Pablo Ricardo");
    }
    
    @Transactional
    @Test
    void delete_pessoaById_statusOk() throws Exception {
        Long idPessoa = 100001L;
        Pessoa pessoaBefore = new Pessoa();
        pessoaBefore.setId(idPessoa);
        pessoaBefore.setNome("Rebeca");
        pessoaBefore.setCpf("123.456.789-00");
        pessoaRepository.salvar(pessoaBefore);
        
        mockMvc.perform(delete("/api/pessoa/" + idPessoa)).andExpect(status().isOk());
        
        Pessoa pessoaAfter = pessoaRepository.pesquisaPorId(idPessoa);
        assertNull(pessoaAfter);
        
    }
}
