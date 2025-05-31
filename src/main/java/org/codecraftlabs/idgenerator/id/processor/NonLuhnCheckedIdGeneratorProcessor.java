package org.codecraftlabs.idgenerator.id.processor;

import org.codecraftlabs.idgenerator.id.IdGenerationProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;

@Service("nonluhn")
class NonLuhnCheckedIdGeneratorProcessor implements IdGenerationProcessor {
    private static final Logger logger = LoggerFactory.getLogger(NonLuhnCheckedIdGeneratorProcessor.class);

    private final SimpleIdGeneratorUtil simpleIdGeneratorUtil;
    private final LuhnValidator luhnValidator;

    @Autowired
    NonLuhnCheckedIdGeneratorProcessor(@Nonnull SimpleIdGeneratorUtil simpleIdGeneratorUtil, @Nonnull LuhnValidator luhnValidator) {
        this.simpleIdGeneratorUtil = simpleIdGeneratorUtil;
        this.luhnValidator = luhnValidator;
    }

    @Nonnull
    @Override
    public String generateId(@Nonnull String seriesName) {
        String originalValue = simpleIdGeneratorUtil.generateId(seriesName);
        int numberOfCalls = 1;
        while(luhnValidator.isValidLuhn(originalValue)) {
            originalValue = simpleIdGeneratorUtil.generateId(seriesName);
            numberOfCalls++;
        }
        logger.info("Number of calls until finding a non valid luhn checked number: '{}'", numberOfCalls);
        return originalValue;
    }
}
