package com.resilience.exception;

import com.resilience.model.MainModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(Exception.class)
    public final <T> ResponseEntity<MainModel<T>> handleAllExceptions(Exception ex) {
        MainModel<T> mainModel = new MainModel<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage(), null);
        return new ResponseEntity<>(mainModel, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
