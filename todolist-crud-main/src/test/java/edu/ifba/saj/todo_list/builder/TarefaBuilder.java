package edu.ifba.saj.todo_list.builder;

import java.time.LocalDate;
import edu.ifba.saj.todo_list.constants.StatusENUM;
import edu.ifba.saj.todo_list.domain.entity.Tarefa;

public class TarefaBuilder {
    
    public static Tarefa buildTarefaInicial(){
        return Tarefa
        .builder()
        .id(null)
        .titulo("Enviar e-mail")
        .descricao("Enviar e-mail para a coordenacao")
        .status(StatusENUM.PARA_FAZER)
        .dataCriacao(LocalDate.now())
        .build();
    }


    public static Tarefa buildTarefaEsperada(){
        var tarefa = buildTarefaInicial();
        tarefa.setId(1L);
        return tarefa;
    }
}
