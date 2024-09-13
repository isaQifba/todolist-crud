package edu.ifba.saj.todo_list.controller.impl;

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

}
