package edu.ifba.saj.todo_list.service;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;

import edu.ifba.saj.todo_list.builder.TarefaBuilder;
import edu.ifba.saj.todo_list.builder.TarefaDTOBuilder;
import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import edu.ifba.saj.todo_list.domain.entity.Tarefa;
import edu.ifba.saj.todo_list.repository.TarefaRepository;
import edu.ifba.saj.todo_list.service.impl.TarefaServiceImpl;
import edu.ifba.saj.todo_list.mapper.TarefaMapper;
import edu.ifba.saj.todo_list.mapper.TarefaMapperImpl;
import edu.ifba.saj.todo_list.constants.StatusENUM;
import edu.ifba.saj.todo_list.exceptions.NotFoundException;


@ContextConfiguration(classes = {TarefaServiceImpl.class, TarefaMapperImpl.class})
@ExtendWith(SpringExtension.class)
public class TarefaServiceTest {

    @Autowired
    private TarefaService service;

    @MockBean
    private TarefaRepository repository;

    @Autowired
    private TarefaMapper mapper;
    
    @Test
    void deveBuscarTodos() throws Exception {
        var tarefa = TarefaBuilder.buildTarefa();

        given(repository.findAll()).willReturn(List.of(tarefa));

        List<TarefaDTO> response = service.findAll();
        var tarefaEsperada = response.get(0);

        comparaTarefa(tarefaEsperada, tarefa);
    }

    @Test
    void deveBuscarUmaTarefaPorId() throws Exception {
        var tarefa = TarefaBuilder.buildTarefa();
        given(repository.findById(1L)).willReturn(Optional.of(tarefa));
        comparaTarefa(service.findById(1L), tarefa);
    }

    @Test
    void deveNaoAcharTarefaPorId() throws Exception {
        given(repository.findById(1L)).willReturn(Optional.empty());
        var tarefa = service.findById(1L);
        assertEquals(tarefa, null);
    }

    @Test
    void deveBuscarTodosPorStatus() throws Exception {
        var tarefa = TarefaBuilder.buildTarefa();
        var tarefa2 = TarefaBuilder.buildTarefa();
        tarefa2.setId(2L);
        tarefa2.setDataCriacao(LocalDate.of(2024, 9, 29));

        given(repository.findByStatus(StatusENUM.PARA_FAZER)).willReturn(List.of(tarefa, tarefa2));

        List<TarefaDTO> response = service.findByStatus(StatusENUM.PARA_FAZER);
        var tarefaEsperada = response.get(0);

        assertEquals(response.size(), 2);
        comparaTarefa(tarefaEsperada, tarefa);
    }

    @Test
    void deveNaoAcharTarefaPorStatus() throws Exception {

        var status = StatusENUM.fromString("para_fazer");

        given(repository.findByStatus(status)).willReturn(new ArrayList<Tarefa>());

        List<TarefaDTO> response = service.findByStatus(status);

        assertEquals(response.size(), 0);
    }

    @Test
    void deveRemoverTarefa() throws Exception {
        Long id = 1L;

        doNothing().when(repository).deleteById(id);
        given(repository.existsById(id)).willReturn(true);

        service.remove(id);

        verify(repository, times(1)).deleteById(id);
    }

    @Test 
    void deveGerarErrorAoTentarRemoverTarefa() throws Exception {
        Long id = 1L;
        given(repository.existsById(id)).willReturn(false);
        assertThrows(NotFoundException.class, () -> service.remove(id));
    }

    @Test
    void deveCriarTarefa() throws Exception {
        var tarefaEsperada = TarefaBuilder.buildTarefa();
        var tarefaDTO = TarefaDTOBuilder.buildRequest();

        given(repository.save(any(Tarefa.class))).willReturn(tarefaEsperada);

        var tarefaResponse = service.create(tarefaDTO);
        comparaTarefa(tarefaResponse, tarefaEsperada);       
    }

    @Test
    void deveGerarErrorAoAtualizarTarefa() throws Exception {
        var tarefaDTO = TarefaDTOBuilder.buildRequest();

        given(repository.existsById(tarefaDTO.getId())).willReturn(false);
        assertThrows(NotFoundException.class, () -> service.update(tarefaDTO));       
    }

    @Test
    void deveAtualizarTarefa() throws Exception {
        var tarefaEsperada = TarefaBuilder.buildTarefa();
        var tarefaDTO = TarefaDTOBuilder.buildRequest();

        given(repository.existsById(tarefaDTO.getId())).willReturn(true);
        given(repository.save(any(Tarefa.class))).willReturn(tarefaEsperada);

        var tarefaResponse = service.update(tarefaDTO);
        comparaTarefa(tarefaResponse, tarefaEsperada);       
    }

    void comparaTarefa(TarefaDTO tarefaEsperada, Tarefa tarefaMock) {
        assertEquals(tarefaEsperada.getId(), 1L);
        assertEquals(tarefaEsperada.getTitulo(), "Enviar e-mail");
        assertEquals(tarefaEsperada.getDescricao(), "Enviar e-mail para a coordenacao");
        assertEquals(tarefaEsperada.getStatus(), StatusENUM.PARA_FAZER.getStatus().toUpperCase());
        assertEquals(tarefaEsperada.getDataCriacao(), LocalDate.of(2024, 9, 29));
       
    }
   
}
