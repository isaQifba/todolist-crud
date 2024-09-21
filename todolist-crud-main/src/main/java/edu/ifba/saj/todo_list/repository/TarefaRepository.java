package edu.ifba.saj.todo_list.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.ifba.saj.todo_list.domain.entity.Tarefa;

public interface  TarefaRepository extends JpaRepository<Tarefa, Long> {
    
}
