package org.codecraftlabs.idgenerator.id.repository;

public class IdNotGeneratedException extends RuntimeException {
    public IdNotGeneratedException(String message, Throwable exception) {
        super(message, exception);
    }
}
