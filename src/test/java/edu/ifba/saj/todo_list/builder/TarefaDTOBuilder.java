package edu.ifba.saj.todo_list.builder;

import java.time.LocalDate;

import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;

public class TarefaDTOBuilder {


    public static TarefaDTO buildRequest(){
        var dataCriacao = LocalDate.of(2024, 9, 29);
        return TarefaDTO
        .builder()
        .id(1L)
        .titulo("Enviar e-mail")
        .descricao("Enviar e-mail para a coordenacao")
        .status("PARA_FAZER")
        .dataCriacao(dataCriacao)
        .build();
    }


    public static TarefaDTO buildResponse(){
        var tarefa = buildRequest();
        tarefa.setId(null);
        tarefa.setDataCriacao(null);
        return tarefa;
    }
    
}
