package edu.ifba.saj.todo_list.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.ContextConfiguration;

import edu.ifba.saj.todo_list.builder.TarefaBuilder;
import edu.ifba.saj.todo_list.builder.TarefaDTOBuilder;
import edu.ifba.saj.todo_list.domain.entity.Tarefa;
import edu.ifba.saj.todo_list.exceptions.DefaultControllerAdvice;
import edu.ifba.saj.todo_list.repository.TarefaRepository;


@ContextConfiguration(classes = {TarefaService.class, DefaultControllerAdvice.class})
public class TarefaServiceTest {
 
    @Mock
    private TarefaRepository repository;

    @InjectMocks
    private TarefaService service;

    /* @Test
    void deveCriarTarefaComSucesso() throws Exception {

        var estadoInicial = TarefaDTOBuilder.buildRequest();
        var estadoFinal = TarefaDTOBuilder.buildResponse();

        var tarefaInicial = TarefaBuilder.buildTarefaInicial();
        var tarefaEsperada = TarefaBuilder.buildTarefaEsperada();

        BDDMockito.given(Mockito.any(Tarefa.class))
        .willReturn(tarefaEsperada);

        var tarefaCriada = service.create(estadoInicial);

        BDDMockito.then(repository.save(tarefaInicial));
        Assertions.assertEquals(tarefaCriada, estadoFinal);

    } */
   
}
