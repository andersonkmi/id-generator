package org.codecraftlabs.idgenerator.id.util;

public class DatabaseException extends RuntimeException {
    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(String message, Throwable exception) {
        super(message, exception);
    }
}
