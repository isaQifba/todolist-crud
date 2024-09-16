package edu.ifba.saj.todo_list.builder;

import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;

public class TarefaDTOBuilder {


    public static TarefaDTO buildRequest(){
        return TarefaDTO
        .builder()
        .id(null)
        .titulo("Enviar e-mail")
        .descricao("Enviar e-mail para a coordenacao")
        .status("PARA_FAZER")
        .build();
    }


    public static TarefaDTO buildResponse(){
        var tarefa = buildRequest();
        tarefa.setId(1L);
        return tarefa;
    }
    
}
