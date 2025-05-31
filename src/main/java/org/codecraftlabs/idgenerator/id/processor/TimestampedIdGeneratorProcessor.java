package org.codecraftlabs.idgenerator.id.processor;

import org.codecraftlabs.idgenerator.id.IdGenerationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.time.Instant;

import static java.lang.String.format;

@Service("timestamped")
class TimestampedIdGeneratorProcessor implements IdGenerationProcessor {
    private final SimpleIdGeneratorUtil simpleIdGeneratorUtil;

    @Autowired
    TimestampedIdGeneratorProcessor(@Nonnull SimpleIdGeneratorUtil simpleIdGeneratorUtil) {
        this.simpleIdGeneratorUtil = simpleIdGeneratorUtil;
    }

    @Nonnull
    @Override
    public String generateId(@Nonnull String seriesName) {
        long id = simpleIdGeneratorUtil.generateLongId(seriesName);
        return format("%015d-%d", id, Instant.now().toEpochMilli());
    }
}
