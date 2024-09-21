package edu.ifba.saj.todo_list.controller.impl;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.ifba.saj.todo_list.controller.TarefaControllerAPI;
import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import edu.ifba.saj.todo_list.service.TarefaService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class TarefaController implements TarefaControllerAPI {
    
    private final TarefaService service;

    public TarefaDTO create(TarefaDTO tarefaDTO){

        return service.create(tarefaDTO);

    }

    public TarefaDTO update(Long id, TarefaDTO tarefaDTO) {
        tarefaDTO.setId(id);
        return service.update(tarefaDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public void remove(@PathVariable Long id) {
        service.remove(id);
    }

}
