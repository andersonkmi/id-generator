package org.codecraftlabs.idgenerator.id.repository;

public class SequenceNotFoundException extends DatabaseException {
    public SequenceNotFoundException(String message) {
        super(message);
    }
}
