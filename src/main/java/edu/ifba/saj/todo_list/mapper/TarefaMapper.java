package edu.ifba.saj.todo_list.mapper;

import org.mapstruct.Mapper;

import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import edu.ifba.saj.todo_list.domain.entity.Tarefa;

@Mapper(componentModel = "spring")
public abstract class TarefaMapper {
    
    public abstract TarefaDTO toTarefaDTO(Tarefa tarefa);

    public abstract Tarefa toTarefa(TarefaDTO tarefaDTO);

}
