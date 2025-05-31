package org.codecraftlabs.idgenerator.id.processor;

import org.codecraftlabs.idgenerator.id.IdGenerationProcessor;
import org.codecraftlabs.idgenerator.id.repository.DatabaseException;
import org.codecraftlabs.idgenerator.id.repository.IdGenerationRepository;
import org.codecraftlabs.idgenerator.id.repository.SequenceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Base64;

@Service("base64")
public class Base64IdGeneratorProcessor implements IdGenerationProcessor {
    private final IdGenerationRepository idGenerationRepository;

    @Autowired
    public Base64IdGeneratorProcessor(@Nonnull IdGenerationRepository idGenerationRepository) {
        this.idGenerationRepository = idGenerationRepository;
    }

    @Nonnull
    @Override
    public String generateId(@Nonnull String seriesName) {
        try {
            long value = idGenerationRepository.getId(seriesName);
            String padded = String.format("%010d", value);
            return Base64.getEncoder().encodeToString(padded.getBytes());
        } catch (SequenceNotFoundException exception) {
            throw new InvalidSeriesException("Invalid series provided", exception);
        } catch (DatabaseException exception) {
            throw new IdNotGeneratedException("Failed to generate next id", exception);
        }
    }
}
