package edu.ifba.saj.todo_list.Exceptions;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DefaultControllerAdvice {
    
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidExecption(MethodArgumentNotValidException ex){

        List<String> camposErros = new ArrayList<>();
        for (var error : ex.getFieldErrors()) {
            camposErros.add(String.format("%s: %s", error.getField(), error.getDefaultMessage()));
        }

        ErrorResponse error = new ErrorResponse("Erro de validação", ex.getMessage(), camposErros);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex){

        ErrorResponse error = new ErrorResponse("Erro na requisição", ex.getMessage(), null);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
