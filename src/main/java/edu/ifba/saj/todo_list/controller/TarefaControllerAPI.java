package edu.ifba.saj.todo_list.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import jakarta.validation.Valid;

@RequestMapping("/tarefa")
public interface TarefaControllerAPI {
    
    @PostMapping
    TarefaDTO create(@RequestBody @Valid TarefaDTO t);
}
