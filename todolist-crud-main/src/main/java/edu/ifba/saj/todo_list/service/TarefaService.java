package edu.ifba.saj.todo_list.service;

import java.util.List;

import edu.ifba.saj.todo_list.constants.StatusENUM;
import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;

public interface TarefaService {
    
    TarefaDTO create(TarefaDTO t);

    TarefaDTO update(TarefaDTO t);

    void remove(Long id);

    List<TarefaDTO> findAll();

    TarefaDTO findById(Long id);

    List<TarefaDTO> findByStatus(StatusENUM status);

}
