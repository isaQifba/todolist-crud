package edu.ifba.saj.todo_list.mapper;

import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import edu.ifba.saj.todo_list.domain.entity.Tarefa;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-30T01:36:20-0300",
    comments = "version: 1.6.0, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class TarefaMapperImpl extends TarefaMapper {

    @Override
    public TarefaDTO toTarefaDTO(Tarefa tarefa) {
        if ( tarefa == null ) {
            return null;
        }

        TarefaDTO.TarefaDTOBuilder tarefaDTO = TarefaDTO.builder();

        tarefaDTO.id( tarefa.getId() );
        tarefaDTO.titulo( tarefa.getTitulo() );
        tarefaDTO.descricao( tarefa.getDescricao() );
        if ( tarefa.getStatus() != null ) {
            tarefaDTO.status( tarefa.getStatus().name() );
        }
        tarefaDTO.dataCriacao( tarefa.getDataCriacao() );

        return tarefaDTO.build();
    }

    @Override
    public Tarefa toTarefa(TarefaDTO tarefaDTO) {
        if ( tarefaDTO == null ) {
            return null;
        }

        Tarefa.TarefaBuilder tarefa = Tarefa.builder();

        tarefa.id( tarefaDTO.getId() );
        tarefa.titulo( tarefaDTO.getTitulo() );
        tarefa.descricao( tarefaDTO.getDescricao() );

        tarefa.status( toStatusENUM(tarefaDTO.getStatus()) );
        tarefa.dataCriacao( generateDataCriacao() );

        return tarefa.build();
    }
}
