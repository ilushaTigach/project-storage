package org.telyatenko.storage.service.api.exception;

public class NotFoundStorageException extends RuntimeException{
    public NotFoundStorageException(String name, String value) {
        super(String.format("Storage with '%s' = %s not found", name, value));
    }
}
