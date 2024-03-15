package org.telyatenko.storage.service.domain.exception.handler;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.telyatenko.storage.service.api.exception.*;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(value = {NotFoundProductException.class})
    public ResponseEntity<ResponseError> handleException(NotFoundProductException exception) {
        ResponseError responseError = new ResponseError(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {NotFoundStorageException.class})
    public ResponseEntity<ResponseError> handleException(NotFoundStorageException exception) {
        ResponseError responseError = new ResponseError(
                HttpStatus.NOT_FOUND,
                exception.getMessage()
        );
        return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {RuntimeException.class})
    public ResponseEntity<ResponseError> handleException(RuntimeException exception) {
        ResponseError responseError = new ResponseError(
                HttpStatus.UNPROCESSABLE_ENTITY,
                exception.getMessage()
        );
        return new ResponseEntity<>(responseError, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {ConflictStorageException.class})
    public ResponseEntity<ResponseError> handleException(ConflictStorageException exception) {
        ResponseError responseError = new ResponseError(
                HttpStatus.CONFLICT,
                exception.getMessage()
        );
        return new ResponseEntity<>(responseError, HttpStatus.CONFLICT);
    }
}