package org.codecraftlabs.idgenerator.id.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LuhnValidatorTest {
    private LuhnValidator luhnValidator;

    @BeforeEach
    public void setup() {
        this.luhnValidator = new LuhnValidator();
    }

    @Test
    public void when_number_is_valid_should_return_true() {
        assertThat(this.luhnValidator.isValid("18")).isTrue();
    }

    @Test
    public void when_number_is_invalid_should_return_false() {
        assertThat(this.luhnValidator.isValid("123")).isFalse();
    }
}
