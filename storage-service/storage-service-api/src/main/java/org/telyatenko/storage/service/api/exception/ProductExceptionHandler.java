package org.telyatenko.storage.service.api.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.sql.SQLException;

@ControllerAdvice
public class ProductExceptionHandler {

    @ExceptionHandler(value = {RequiredException.class})
    public ResponseEntity<Object> handelException(RequiredException exception){
        ProductResponseError productResponseError = new ProductResponseError(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                exception
                );
        return new ResponseEntity<>(productResponseError, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ProductResponseError> handelException(Exception exception) {
//        var response = ProductResponseError.build(exception, HttpStatus.INTERNAL_SERVER_ERROR);
//
//        return new ResponseEntity<>(response, response.getStatus());
//    }
//
//    @ExceptionHandler(SQLException.class)
//    public ResponseEntity<ProductResponseError> handleException(SQLException exception) {
//        var response = ProductResponseError.build(exception, HttpStatus.INTERNAL_SERVER_ERROR);
//
//        return new ResponseEntity<>(response, response.getStatus());
//    }
}
