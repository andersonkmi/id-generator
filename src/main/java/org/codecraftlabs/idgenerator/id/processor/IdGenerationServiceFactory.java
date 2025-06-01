package org.codecraftlabs.idgenerator.id.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;

import static java.util.Optional.ofNullable;

@Component
public class IdGenerationServiceFactory {
    private final Map<String, IdGenerationProcessor> processors;

    @Autowired
    public IdGenerationServiceFactory(Map<String, IdGenerationProcessor> processors) {
        this.processors = processors;
    }

    @Nonnull
    public Optional<IdGenerationProcessor> getProcessor(@Nonnull String type) {
        return ofNullable(this.processors.get(type));
    }
}
