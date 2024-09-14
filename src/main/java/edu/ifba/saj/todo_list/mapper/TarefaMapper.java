package edu.ifba.saj.todo_list.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import edu.ifba.saj.todo_list.constants.StatusENUM;
import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import edu.ifba.saj.todo_list.domain.entity.Tarefa;

@Mapper(componentModel = "spring")
public abstract class TarefaMapper {
    
    
    public abstract TarefaDTO toTarefaDTO(Tarefa tarefa);
    
    @Mapping(target = "status", expression = "java(toStatusENUM(tarefaDTO.getStatus()))")
    public abstract Tarefa toTarefa(TarefaDTO tarefaDTO);

    public StatusENUM toStatusENUM(String s){
        return StatusENUM.fromString(s);
    }
}
