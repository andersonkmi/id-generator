package org.codecraftlabs.idgenerator.id.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service("default")
class DefaultIdGeneratorProcessor implements IdGenerationProcessor {
    private final SimpleIdGeneratorUtil simpleIdGeneratorUtil;

    @Autowired
    DefaultIdGeneratorProcessor(@Nonnull SimpleIdGeneratorUtil simpleIdGeneratorUtil) {
        this.simpleIdGeneratorUtil = simpleIdGeneratorUtil;
    }

    @Nonnull
    public String generateId(@Nonnull String seriesName) {
        return simpleIdGeneratorUtil.generateId(seriesName);
    }
}
