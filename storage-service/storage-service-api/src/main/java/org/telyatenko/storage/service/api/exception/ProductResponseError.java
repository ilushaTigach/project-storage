package org.telyatenko.storage.service.api.exception;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.HttpStatus;

@Data
@Accessors(chain = true)
public class ProductResponseError {



    private final HttpStatus status;

    private final String message;

    private final Throwable throwable;

    public ProductResponseError(HttpStatus status,
                                String message,
                                Throwable throwable) {
        this.status = status;
        this.message = message;
        this.throwable = throwable;
    }

//    public static ProductResponseError build(Exception exception, HttpStatus status) {
//        return new ProductResponseError()
//                .setMessage(exception.getMessage())
//                .setStatus(status);
//    }
}
