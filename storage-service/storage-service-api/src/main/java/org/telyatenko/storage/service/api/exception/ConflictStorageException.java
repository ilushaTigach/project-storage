package org.telyatenko.storage.service.api.exception;

public class ConflictStorageException extends RuntimeException {
    public ConflictStorageException() {
        super("Storage is not available during non-working hours");
    }
}
