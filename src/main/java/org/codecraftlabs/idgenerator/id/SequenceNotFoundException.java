package org.codecraftlabs.idgenerator.id;

public class SequenceNotFoundException extends DatabaseException {
    public SequenceNotFoundException(String message) {
        super(message);
    }
}
