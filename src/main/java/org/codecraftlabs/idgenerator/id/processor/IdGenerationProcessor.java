package org.codecraftlabs.idgenerator.id.processor;

import javax.annotation.Nonnull;

public interface IdGenerationProcessor {
    @Nonnull
    String generateId(@Nonnull String seriesName);
}
