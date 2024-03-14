package org.telyatenko.storage.service.domain.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.telyatenko.storage.service.api.exception.RequiredException;
import org.telyatenko.storage.service.api.exception.RequiredExceptionTwo;
import org.telyatenko.storage.service.api.exception.ResponseError;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {RequiredException.class})
    public ResponseEntity<Object> handelException(RequiredException exception) {
        ResponseError responseError = new ResponseError(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<Object> handelException(RuntimeException exception) {
        ResponseError responseError = new ResponseError(
                HttpStatus.UNPROCESSABLE_ENTITY,
                exception.getMessage()
        );
        return new ResponseEntity<>(responseError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handelException(RequiredExceptionTwo exception) {
        ResponseError responseError = new ResponseError(
                HttpStatus.CONFLICT,
                exception.getMessage()
        );
        return new ResponseEntity<>(responseError, HttpStatus.CONFLICT);
    }
}