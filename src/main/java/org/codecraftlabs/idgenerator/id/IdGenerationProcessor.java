package org.codecraftlabs.idgenerator.id;

import javax.annotation.Nonnull;

public interface IdGenerationProcessor {
    String generateId(@Nonnull String seriesName);
}
