package org.codecraftlabs.idgenerator.id.util;

import org.springframework.stereotype.Component;

import javax.annotation.Nonnull;

@Component
public class LuhnValidator {
    public boolean isValid(@Nonnull String number) {
        int sum = 0;
        boolean alternate = false;

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

    public boolean isInvalid(@Nonnull String number) {
        return !isValid(number);
    }
}
