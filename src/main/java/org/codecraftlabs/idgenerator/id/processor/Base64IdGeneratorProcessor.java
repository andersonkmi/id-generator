package org.codecraftlabs.idgenerator.id.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.Base64;

@Service("base64")
class Base64IdGeneratorProcessor implements IdGenerationProcessor {
    private final SimpleIdGeneratorUtil simpleIdGeneratorUtil;

    @Autowired
    Base64IdGeneratorProcessor(@Nonnull SimpleIdGeneratorUtil simpleIdGeneratorUtil) {
        this.simpleIdGeneratorUtil = simpleIdGeneratorUtil;
    }

    @Nonnull
    @Override
    public String generateId(@Nonnull String seriesName) {
        String value = simpleIdGeneratorUtil.generateId(seriesName);
        return Base64.getEncoder().encodeToString(value.getBytes());
    }
}
