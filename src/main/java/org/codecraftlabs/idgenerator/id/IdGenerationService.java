package org.codecraftlabs.idgenerator.id;

import javax.annotation.Nonnull;

public interface IdGenerationService {
    String generateId(@Nonnull String seriesName);
}
