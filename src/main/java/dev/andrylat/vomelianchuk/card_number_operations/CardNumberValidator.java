package dev.andrylat.vomelianchuk.card_number_operations;

import java.util.ArrayList;
import java.util.List;

public class CardNumberValidator {
    private static final String HIATUS_SEPARATOR = "\\s";
    private static final int LENGTH_CARD_NUMBER = 16;
    private static final String ERROR_NULL_CARD_NUMBER = "Error receiving null object instead of card number";
    private static final String ERROR_LENGTH_CARD = "Length should be 16 symbols";
    private static final String ERROR_ONLY_DIGITS = "Number should contain only digits";

    private final List<String> errors = new ArrayList<>();

    public void validate(String cardNumber) throws CardValidationException {
        if (cardNumber != null) {
            cardNumber = transform(cardNumber);
            checkLengthCardNumber(cardNumber);
            checkSymbolsCardNumber(cardNumber);
            new LuhnAlgorithm().checkLastNumber(cardNumber, errors);
        } else {
            errors.add(ERROR_NULL_CARD_NUMBER);
            throw new CardValidationException(errors);
        }
    }

    private String transform(String cardNumber) {
        return cardNumber.replaceAll(HIATUS_SEPARATOR, "");
    }

    private void checkLengthCardNumber(String cardNumber) {
        if (cardNumber.length() != LENGTH_CARD_NUMBER) {
            errors.add(ERROR_LENGTH_CARD);
            if ((cardNumber.isEmpty())) {
                throw new CardValidationException(errors);
            }
        }
    }

    private void checkSymbolsCardNumber(String cardNumber) {
        int i = 0;
        while (i < cardNumber.length()) {
            if (Character.isLetter(cardNumber.charAt(i))) {
                errors.add(ERROR_ONLY_DIGITS);
                break;
            }
            i++;
        }
    }

}
