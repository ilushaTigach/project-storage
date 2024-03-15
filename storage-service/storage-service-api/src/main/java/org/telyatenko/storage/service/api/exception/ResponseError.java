package org.telyatenko.storage.service.api.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseError {

    private final HttpStatus status;

    private final String message;

    public ResponseError(HttpStatus status,
                         String message
                                ) {
        this.status = status;
        this.message = message;

    }
}
