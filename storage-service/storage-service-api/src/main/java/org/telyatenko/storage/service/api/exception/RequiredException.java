package org.telyatenko.storage.service.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ResponseStatusException;

public class RequiredException extends RuntimeException {
    public RequiredException(String message) {
        super(message);
    }
}
