package edu.ifba.saj.todo_list.builder;

import java.time.LocalDate;
import edu.ifba.saj.todo_list.constants.StatusENUM;
import edu.ifba.saj.todo_list.domain.entity.Tarefa;

public class TarefaBuilder {
    
    public static Tarefa buildTarefa(){
        var dataCriacao = LocalDate.of(2024, 9, 29);
        return Tarefa
        .builder()
        .id(1L)
        .titulo("Enviar e-mail")
        .descricao("Enviar e-mail para a coordenacao")
        .status(StatusENUM.PARA_FAZER)
        .dataCriacao(dataCriacao)
        .build();
    }
}
