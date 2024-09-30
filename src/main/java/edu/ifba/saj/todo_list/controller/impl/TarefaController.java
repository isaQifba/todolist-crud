package edu.ifba.saj.todo_list.controller.impl;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import edu.ifba.saj.todo_list.controller.TarefaControllerAPI;
import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import edu.ifba.saj.todo_list.service.TarefaService;
import edu.ifba.saj.todo_list.constants.StatusENUM;
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

    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.remove(id);
        return ResponseEntity.noContent().build();
    }

    public List<TarefaDTO> getAll() {
        return service.findAll();
    }

    public ResponseEntity<TarefaDTO> getById(Long id) {
        TarefaDTO p = service.findById(id);
        if (p != null) {
            return ResponseEntity.ok(p);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    public List<TarefaDTO> getAll(String status) {
        return service.findByStatus(StatusENUM.fromString(status));
    }

}
