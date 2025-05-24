package org.codecraftlabs.idgenerator.id;

import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service("DEFAULT")
public class DefaultIdGeneratorService implements IdGenerationService {
    @Nonnull
    public String generateId(@Nonnull String seriesName) {
        return "";
    }
}
