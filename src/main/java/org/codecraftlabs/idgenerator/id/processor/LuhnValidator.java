package org.codecraftlabs.idgenerator.id.processor;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
class LuhnValidator {
    boolean isValidLuhn(@Nonnull String number) {
        int sum = 0;
        boolean alternate = false;

        // Start from the rightmost digit and work left
        for (int i = number.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(number.charAt(i));

            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            alternate = !alternate;
        }

        return sum % 10 == 0;
    }
}
