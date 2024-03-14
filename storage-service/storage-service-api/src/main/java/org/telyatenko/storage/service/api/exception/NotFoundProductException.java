package org.telyatenko.storage.service.api.exception;

public class NotFoundProductException extends RequiredException {
    public NotFoundProductException(String name, String value) {
        super(String.format("Product with '%s' = %s not found", name, value));
    }
}
