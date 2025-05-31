package org.codecraftlabs.idgenerator.id.processor;

import org.codecraftlabs.idgenerator.id.repository.DatabaseException;
import org.codecraftlabs.idgenerator.id.repository.IdGenerationRepository;
import org.codecraftlabs.idgenerator.id.repository.SequenceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
class SimpleIdGeneratorUtil {
    private final IdGenerationRepository idGenerationRepository;

    @Autowired
    SimpleIdGeneratorUtil(IdGenerationRepository idGenerationRepository) {
        this.idGenerationRepository = idGenerationRepository;
    }

    @Nonnull
    String generateId(@Nonnull String seriesName) {
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
