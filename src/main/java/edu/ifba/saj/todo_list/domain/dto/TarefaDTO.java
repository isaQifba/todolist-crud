package edu.ifba.saj.todo_list.domain.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import edu.ifba.saj.todo_list.constants.StatusENUM;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TarefaDTO {
    
    private Long id;

    @NotEmpty
    @Size(max = 100)
    private String titulo;

    @NotEmpty
    private String descricao;

    @NotEmpty
    private String status;

    private LocalDate dataCriacao;
}
