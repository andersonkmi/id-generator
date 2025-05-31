package org.codecraftlabs.idgenerator.id;

import org.codecraftlabs.idgenerator.id.util.DatabaseException;

public class SequenceNotFoundException extends DatabaseException {
    public SequenceNotFoundException(String message) {
        super(message);
    }
}
