package edu.ifba.saj.todo_list.service.impl;

import java.util.List;

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
    public TarefaDTO create(TarefaDTO tarefaDTO) {
        
        if(tarefaDTO.getId() != null)
        throw new IllegalArgumentException("O Id não deve ser especificado na criação de uma nova tarefa.");

        Tarefa tarefa = repository.save(mapper.toTarefa(tarefaDTO));
        return mapper.toTarefaDTO(tarefa);
    }
    @Override
    public List<TarefaDTO> findAll() {

        return null;
    }
    @Override
    public TarefaDTO findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public List<TarefaDTO> findByStatus(StatusENUM status) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void remove(Long id) {
        if (!repository.existsById(id)) {
            throw new NotFoundException("Tarefa não encontrada com id " + id);
        }
        repository.deleteById(id);
    }

    @Override
    public TarefaDTO update(TarefaDTO t) {
        if (!repository.existsById(t.getId())) {
            throw new NotFoundException("Tarefa não encontrada com id " + t.getId());
        }
    
        Tarefa tarefaAtualizada = mapper.toTarefa(t);
        tarefaAtualizada = repository.save(tarefaAtualizada);
        return mapper.toTarefaDTO(tarefaAtualizada);
    }
}
