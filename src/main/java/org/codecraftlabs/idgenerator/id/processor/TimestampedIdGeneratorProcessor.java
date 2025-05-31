package org.codecraftlabs.idgenerator.id.processor;

import org.codecraftlabs.idgenerator.id.IdGenerationProcessor;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service("timestamped")
class TimestampedIdGeneratorProcessor implements IdGenerationProcessor {
    @Nonnull
    @Override
    public String generateId(@Nonnull String seriesName) {
        return "";
    }
}
