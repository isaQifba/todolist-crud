package edu.ifba.saj.todo_list.domain.entity;

import java.time.LocalDate;
import edu.ifba.saj.todo_list.constants.StatusENUM;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tarefas")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    @Enumerated(EnumType.STRING)
    private StatusENUM status;

    @Column(name = "data_criacao")
    private LocalDate dataCriacao;
}
