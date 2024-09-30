package edu.ifba.saj.todo_list.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifba.saj.todo_list.constants.StatusENUM;
import edu.ifba.saj.todo_list.domain.entity.Tarefa;

public interface  TarefaRepository extends JpaRepository<Tarefa, Long> {
    
    public List<Tarefa> findByStatus(StatusENUM status);
}
