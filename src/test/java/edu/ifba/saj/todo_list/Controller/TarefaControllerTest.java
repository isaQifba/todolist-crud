package edu.ifba.saj.todo_list.controller;

import java.util.List;

import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ifba.saj.todo_list.builder.TarefaDTOBuilder;
import edu.ifba.saj.todo_list.controller.impl.TarefaController;
import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import edu.ifba.saj.todo_list.exceptions.DefaultControllerAdvice;
import edu.ifba.saj.todo_list.service.TarefaService;
import static edu.ifba.saj.todo_list.constants.ApiPathConstants.*;
import edu.ifba.saj.todo_list.constants.StatusENUM;
import edu.ifba.saj.todo_list.exceptions.NotFoundException;

@ContextConfiguration(classes = {TarefaController.class, DefaultControllerAdvice.class})
@WebMvcTest(controllers = {TarefaController.class})
@AutoConfigureMockMvc
public class TarefaControllerTest {
    
    @MockBean
    private TarefaService service;
    
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void deveCriarTarefaComSucesso() throws Exception {
        var request = TarefaDTOBuilder.buildRequest();
        var responseEsperada = TarefaDTOBuilder.buildResponse();

        given(service.create(Mockito.any(TarefaDTO.class)))
            .willReturn(responseEsperada);

        mockMvc.perform(
            post(API_TAREFA)
            .content(mapper.writeValueAsString(request))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(mapper.writeValueAsString(responseEsperada)));

    }

    @Test
    void deveRetornarErroComRequestInvalido() throws Exception {
        var request = new TarefaDTO();

        mockMvc.perform(
            post(API_TAREFA)
            .content(mapper.writeValueAsString(request))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest());
    }

    @Test
    void deveAtualizarTarefaComSucesso() throws Exception {
        var request = TarefaDTOBuilder.buildRequest();
        var responseEsperada = TarefaDTOBuilder.buildResponse();

        given(service.update(request)).willReturn(responseEsperada);

        mockMvc.perform(
            put(API_TAREFA + "/1")
            .content(mapper.writeValueAsString(request))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().string(mapper.writeValueAsString(responseEsperada)));
    }

    @Test
    void deveFalharAoTentarAtualizarTarefa() throws Exception {
        var request = TarefaDTOBuilder.buildRequest();
        given(service.update(any(TarefaDTO.class))).willThrow(new NotFoundException(""));

        mockMvc.perform(
            put(API_TAREFA + "/2")
            .content(mapper.writeValueAsString(request))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    void deveBuscarTodasAsTarefas() throws Exception {
        var p1 = TarefaDTOBuilder.buildResponse();
        p1.setId(1L);
        var p2 = TarefaDTOBuilder.buildResponse();
        p2.setId(2L);
        p2.setTitulo("Estudar para PWEB");

        var responseEsperada = List.of(p1, p2);

        given(service.findAll()).willReturn(responseEsperada);

        mockMvc.perform(
            get(API_TAREFA)
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value("1"))
        .andExpect(jsonPath("$[1].titulo").value("Estudar para PWEB"));
    }

    @Test
    void deveAcharUmaTarefa() throws Exception {
        var tarefa = TarefaDTOBuilder.buildRequest();
        given(service.findById(1L)).willReturn(tarefa);

        mockMvc.perform(
            get(API_TAREFA + "/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value("1"))
        .andExpect(jsonPath("$.titulo").value("Enviar e-mail"));
    }

    @Test
    void deveFalharAoTentarAcharTarefa() throws Exception {
        given(service.findById(any(Long.class))).willReturn(null);

        mockMvc.perform(
            get(API_TAREFA + "/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNotFound());
    }

    @Test
    void deveRemoverTarefaComSucesso() throws Exception {
        mockMvc.perform(
            delete(API_TAREFA + "/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNoContent());

        Mockito.verify(service, Mockito.times(1)).remove(1L);
    }

    @Test
    void deveRemoverSemSucesso() throws Exception {
        Mockito.doThrow(new NotFoundException(null)).when(service).remove(1L);        

        mockMvc.perform(
            delete(API_TAREFA + "/1")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void deveBuscarTarefasPorStatus() throws Exception {
        var p1 = TarefaDTOBuilder.buildResponse();
        p1.setId(1L);
        var p2 = TarefaDTOBuilder.buildResponse();
        p2.setId(2L);
        p2.setStatus("para_fazer");

        var responseEsperada = List.of(p1, p2);

        given(service.findByStatus(StatusENUM.fromString("para_fazer")))
            .willReturn(responseEsperada);

        mockMvc.perform(
            get(API_TAREFA + "/status/{status}", "para_fazer")
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(jsonPath("$[0].id").value("1"))
        .andExpect(jsonPath("$[1].status").value("para_fazer"));
    }
}
