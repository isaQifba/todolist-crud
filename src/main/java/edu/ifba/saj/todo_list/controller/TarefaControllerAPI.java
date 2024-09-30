package edu.ifba.saj.todo_list.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import jakarta.validation.Valid;

import static edu.ifba.saj.todo_list.constants.ApiPathConstants.*;

@RequestMapping(API_TAREFA)
public interface TarefaControllerAPI {
    
    @PostMapping
    TarefaDTO create(@RequestBody @Valid TarefaDTO t);
        
    @PutMapping("/{id}")
    TarefaDTO update(@PathVariable("id") Long id, @RequestBody @Valid TarefaDTO tarefaDTO);

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable("id") Long id);

    @GetMapping
    List<TarefaDTO> getAll();

    @GetMapping("/{id}")
    ResponseEntity<TarefaDTO> getById(@PathVariable("id") Long id);

    @GetMapping("/status/{status}")
    public List<TarefaDTO> getAll(@PathVariable("status") String status);
}
