package org.telyatenko.storage.service.api.exception;

public class RequiredException extends RuntimeException {
    public RequiredException(String message) {
        super(message);
    }
}
