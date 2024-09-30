package edu.ifba.saj.todo_list.mapper;

import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import edu.ifba.saj.todo_list.domain.entity.Tarefa;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-09-29T23:02:58-0300",
    comments = "version: 1.6.0, compiler: Eclipse JDT (IDE) 3.40.0.v20240919-1711, environment: Java 17.0.12 (Eclipse Adoptium)"
)
@Component
public class TarefaMapperImpl extends TarefaMapper {

    @Override
    public TarefaDTO toTarefaDTO(Tarefa tarefa) {
        if ( tarefa == null ) {
            return null;
        }

        TarefaDTO.TarefaDTOBuilder tarefaDTO = TarefaDTO.builder();

        tarefaDTO.dataCriacao( tarefa.getDataCriacao() );
        tarefaDTO.descricao( tarefa.getDescricao() );
        tarefaDTO.id( tarefa.getId() );
        if ( tarefa.getStatus() != null ) {
            tarefaDTO.status( tarefa.getStatus().name() );
        }
        tarefaDTO.titulo( tarefa.getTitulo() );

        return tarefaDTO.build();
    }

    @Override
    public Tarefa toTarefa(TarefaDTO tarefaDTO) {
        if ( tarefaDTO == null ) {
            return null;
        }

        Tarefa.TarefaBuilder tarefa = Tarefa.builder();

        tarefa.descricao( tarefaDTO.getDescricao() );
        tarefa.id( tarefaDTO.getId() );
        tarefa.titulo( tarefaDTO.getTitulo() );

        tarefa.status( toStatusENUM(tarefaDTO.getStatus()) );
        tarefa.dataCriacao( generateDataCriacao() );

        return tarefa.build();
    }
}
