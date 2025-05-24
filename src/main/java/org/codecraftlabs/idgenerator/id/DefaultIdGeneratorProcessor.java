package org.codecraftlabs.idgenerator.id;

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
            return idGenerationRepository.getId(seriesName);
        } catch (DatabaseException exception) {
            throw new IdNotGeneratedException("Failed to generate next id", exception);
        }
    }
}
