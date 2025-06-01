package org.codecraftlabs.idgenerator.id.processor;

import org.codecraftlabs.idgenerator.id.series.SeriesToSequence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Optional;

import static java.lang.String.format;

@Service("prefixed")
class PrefixedIdGeneratorProcessor implements IdGenerationProcessor {
    private final SimpleIdGeneratorUtil simpleIdGeneratorUtil;

    @Autowired
    PrefixedIdGeneratorProcessor(@Nonnull SimpleIdGeneratorUtil simpleIdGeneratorUtil) {
        this.simpleIdGeneratorUtil = simpleIdGeneratorUtil;
    }

    @Nonnull
    @Override
    public String generateId(@Nonnull String seriesName) {
        long id = simpleIdGeneratorUtil.generateLongId(seriesName);
        Optional<SeriesToSequence> seriesToSequence = SeriesToSequence.findByName(seriesName);
        if (seriesToSequence.isEmpty()) {
            throw new InvalidSeriesException("Series not mapped yet");
        }
        String prefix = seriesToSequence.get().getPrefix();
        return format("%s%015d", prefix, id);
    }
}
