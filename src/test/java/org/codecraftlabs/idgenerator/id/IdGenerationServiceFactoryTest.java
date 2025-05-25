package org.codecraftlabs.idgenerator.id;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class IdGenerationServiceFactoryTest {
    @Autowired
    private IdGenerationServiceFactory idGenerationServiceFactory;

    @Test
    public void defaultProcessorShouldBePresent() {
        var result = idGenerationServiceFactory.getProcessor("default");
        assertThat(result).isPresent();
    }
}