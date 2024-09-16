package edu.ifba.saj.todo_list.Controller;

import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import edu.ifba.saj.todo_list.Exceptions.DefaultControllerAdvice;
import edu.ifba.saj.todo_list.builder.TarefaDTOBuilder;
import edu.ifba.saj.todo_list.controller.impl.TarefaController;
import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import edu.ifba.saj.todo_list.service.TarefaService;

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
    void deveCriarPessoaComSucesso() throws Exception {
        var request = TarefaDTOBuilder.buildRequest();
        var response = TarefaDTOBuilder.buildResponse();

        BDDMockito.given(service.create(Mockito.any(TarefaDTO.class))).willReturn(response);

        mockMvc.perform(
            MockMvcRequestBuilders.post("/tarefa")
            .content(mapper.writeValueAsString(request))
            .accept(MediaType.APPLICATION_JSON)
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().string(mapper.writeValueAsString(response)));

    }
}
