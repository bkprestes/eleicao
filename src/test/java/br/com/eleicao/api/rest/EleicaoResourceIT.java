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
import br.com.eleicao.api.helper.JsonHelper;
import br.com.eleicao.api.repository.EleicaoRepository;

@SpringBootTest
@AutoConfigureMockMvc

public class EleicaoResourceIT {

    @Autowired
    MockMvc mockMvc;
    
    @Autowired
    EleicaoRepository eleicaoRepository;
    
    @Transactional
    @Test
    void create_eleicao_StatusCreated() throws Exception {
        Eleicao eleicao = new Eleicao();
        eleicao.setId(100004L);
        eleicao.setNome("Cipa");
        eleicao.setData(LocalDate.parse("2020-10-02"));
        
        MockHttpServletResponse response = mockMvc.perform(
                post("/api/eleicoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonHelper.toJson(eleicao))
                ).andExpect(status().isCreated()).andReturn().getResponse();
        
        Eleicao eleicaoCriada = JsonHelper.toObject(response.getContentAsByteArray(), Eleicao.class);
        assertNotNull(eleicaoCriada.getId());
        
        Eleicao eleicaoPersistida = eleicaoRepository.pesquisaPorId(eleicaoCriada.getId());
        assertEquals(eleicao.getNome(), eleicaoPersistida.getNome());
        assertEquals(eleicao.getData(), eleicaoPersistida.getData());
    }
    
    @Transactional
    @Test
    void get_byId_ok() throws Exception {
        Long idEleicao = 100001L;
        
        MockHttpServletResponse response = mockMvc.perform(get("/api/eleicoes/" + idEleicao)).andExpect(status().isOk()).andReturn().getResponse();
        Eleicao eleicaoFound = JsonHelper.toObject(response.getContentAsByteArray(), Eleicao.class);
        
        assertEquals(100001, eleicaoFound.getId());
        assertEquals("Uma eleição 1", eleicaoFound.getNome());
        assertEquals("2020-12-12", eleicaoFound.getData().toString());
    }
    
    @Transactional
    @Test
    void get_allEleicoes_ok() throws Exception {
     
        
        MockHttpServletResponse response = mockMvc.perform(get("/api/eleicoes")).andExpect(status().isOk()).andReturn().getResponse();
        Eleicao[] eleicaoFound = JsonHelper.toObject(response.getContentAsByteArray(), Eleicao[].class);
        
        assertEquals(eleicaoFound[0].getId(), 100001L);
        assertEquals(eleicaoFound[0].getNome(), "Uma eleição 1");
        assertEquals(eleicaoFound[0].getData().toString(), "2020-12-12");
    }
    
    @Transactional
    @Test
    void update_eleicaoById_statusOk() throws Exception {
        Long idEleicao = 100001L;
        Eleicao eleicaoBefore = eleicaoRepository.pesquisaPorId(idEleicao);
        
        Eleicao eleicaoAfter = new Eleicao();
        eleicaoAfter.setId(idEleicao);
        eleicaoAfter.setNome("eleição 2");
        eleicaoAfter.setData(eleicaoBefore.getData());
        
        MockHttpServletResponse response = mockMvc.perform(put("/api/eleicoes/" + idEleicao)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonHelper.toJson(eleicaoAfter))).andExpect(status().isOk()).andReturn().getResponse();
        Eleicao eleicaoFound = JsonHelper.toObject(response.getContentAsByteArray(), Eleicao.class);
        
        assertNotEquals(eleicaoFound.getNome(), eleicaoBefore.getNome());
        assertEquals(eleicaoFound.getNome(), "eleição 2");
        System.out.println(eleicaoFound.getNome()+" "+eleicaoBefore.getNome());
    }
    
    @Transactional
    @Test
    void delete_eleicaoById_statusOk() throws Exception {
        Long idEleicao = 100001L;
        
        mockMvc.perform(delete("/api/eleicoes/" + idEleicao)).andExpect(status().isOk());
        
        Eleicao eleicaoAfter = eleicaoRepository.pesquisaPorId(idEleicao);
        assertNull(eleicaoAfter);
        
    }
}
