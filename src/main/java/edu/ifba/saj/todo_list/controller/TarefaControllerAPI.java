package edu.ifba.saj.todo_list.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import jakarta.validation.Valid;

@RequestMapping("/tarefa")
public interface TarefaControllerAPI {
    
    @PostMapping
    TarefaDTO create(@RequestBody @Valid TarefaDTO t);

        
    @PutMapping("/{id}")
    TarefaDTO update(@PathVariable("id") Long id, @RequestBody @Valid TarefaDTO tarefaDTO);

    @DeleteMapping("/{id}")
    void remove(@PathVariable("id") Long id);

}
