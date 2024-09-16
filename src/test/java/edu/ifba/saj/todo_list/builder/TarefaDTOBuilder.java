package edu.ifba.saj.todo_list.builder;

import java.time.LocalDate;

import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;

public class TarefaDTOBuilder {


    public static TarefaDTO buildRequest(){
        return TarefaDTO
        .builder()
        .id(null)
        .titulo("Enviar e-mail")
        .descricao("Enviar e-mail para a coordenacao")
        .status("PARA_FAZER")
        .dataCriacao(null)
        .build();
    }


    public static TarefaDTO buildResponse(){
        var tarefa = buildRequest();
        tarefa.setId(1L);
        tarefa.setDataCriacao(LocalDate.now());
        return tarefa;
    }
    
}
