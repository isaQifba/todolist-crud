package edu.ifba.saj.todo_list.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import edu.ifba.saj.todo_list.Exceptions.NotFoundException;
import edu.ifba.saj.todo_list.constants.StatusENUM;
import edu.ifba.saj.todo_list.domain.dto.TarefaDTO;
import edu.ifba.saj.todo_list.domain.entity.Tarefa;
import edu.ifba.saj.todo_list.mapper.TarefaMapper;
import edu.ifba.saj.todo_list.repository.TarefaRepository;
import edu.ifba.saj.todo_list.service.TarefaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TarefaServiceImpl implements TarefaService {
    
    private final TarefaRepository repository;
    private final TarefaMapper mapper;

    @Override
    public List<TarefaDTO> findAll() {

        List<Tarefa> tarefas = repository.findAll();

        return tarefas.stream()
        .map(t -> mapper.toTarefaDTO(t))
        .collect(Collectors.toList());
    }

    @Override
    public TarefaDTO findById(Long id) {
        
        Optional<Tarefa> t = repository.findById(id);
        if(t.isPresent()){
            return mapper.toTarefaDTO(t.get());
        }
        return null;
    }

    @Override
    public List<TarefaDTO> findByStatus(StatusENUM status) {
        
        List<Tarefa> tarefas = repository.findByStatus(status);

        return tarefas.stream()
        .map(t -> mapper.toTarefaDTO(t))
        .collect(Collectors.toList());
    }

    @Override
    public void remove(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Tarefa não encontrada com id " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public TarefaDTO create(TarefaDTO tarefaDTO) {
        Tarefa tarefa = repository.save(mapper.toTarefa(tarefaDTO));
        return mapper.toTarefaDTO(tarefa);
    }

    @Override
    public TarefaDTO update(TarefaDTO t) {
        if (!repository.existsById(t.getId())) {
            throw new NotFoundException("Tarefa não encontrada com id " + t.getId());
        }
    
        return create(t);
    }
}
