package edu.ifba.saj.todo_list.Exceptions;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    
    private String titulo;
    private String descricao;
    private List<String> campos;
}
