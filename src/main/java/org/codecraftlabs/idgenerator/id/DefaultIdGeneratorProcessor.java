package org.codecraftlabs.idgenerator.id;

import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service("default")
public class DefaultIdGeneratorProcessor implements IdGenerationProcessor {
    @Nonnull
    public String generateId(@Nonnull String seriesName) {
        return "12345";
    }
}
