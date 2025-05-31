package org.codecraftlabs.idgenerator.id.processor;

import org.codecraftlabs.idgenerator.id.util.DatabaseException;
import org.codecraftlabs.idgenerator.id.IdGenerationProcessor;
import org.codecraftlabs.idgenerator.id.IdGenerationRepository;
import org.codecraftlabs.idgenerator.id.IdNotGeneratedException;
import org.codecraftlabs.idgenerator.id.InvalidSeriesException;
import org.codecraftlabs.idgenerator.id.SequenceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service("default")
class DefaultIdGeneratorProcessor implements IdGenerationProcessor {
    private final IdGenerationRepository idGenerationRepository;

    @Autowired
    public DefaultIdGeneratorProcessor(@Nonnull IdGenerationRepository idGenerationRepository) {
        this.idGenerationRepository = idGenerationRepository;
    }

    @Nonnull
    public String generateId(@Nonnull String seriesName) {
        try {
            long value = idGenerationRepository.getId(seriesName);
            return String.valueOf(value);
        } catch (SequenceNotFoundException exception) {
            throw new InvalidSeriesException("Invalid series provided", exception);
        } catch (DatabaseException exception) {
            throw new IdNotGeneratedException("Failed to generate next id", exception);
        }
    }
}
