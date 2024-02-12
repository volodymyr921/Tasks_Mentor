package dev.andrylat.vomelianchuk.card_number_operations.validation;

import dev.andrylat.vomelianchuk.card_number_operations.enums.PaymentSystem;
import dev.andrylat.vomelianchuk.card_number_operations.exceptions.CardValidationException;

import java.util.ArrayList;
import java.util.List;

public class CardNumberValidator {
    private static final String HIATUS_SEPARATOR = "\\s";
    private static final int LENGTH_CARD_NUMBER = 16;
    private static final String ERROR_NULL_CARD_NUMBER = "Error receiving null object instead of card number";
    private static final String ERROR_LENGTH_CARD = "Length should be 16 symbols";
    private static final String ERROR_ONLY_DIGITS = "Number should contain only digits";

    public PaymentSystem validate(String cardNumber) throws CardValidationException {
        List<String> errors = new ArrayList<>();

        if (cardNumber == null) {
            errors.add(ERROR_NULL_CARD_NUMBER);
            throw new CardValidationException(errors);
        }

        cardNumber = removeSeparators(cardNumber);
        checkLengthCardNumber(cardNumber, errors);
        checkSymbolsCardNumber(cardNumber, errors);
        new LuhnAlgorithm().checkLastNumber(cardNumber, errors);

        return PaymentSystem.determine(cardNumber);
    }

    private String removeSeparators(String cardNumber) {
        return cardNumber.replaceAll(HIATUS_SEPARATOR, "");
    }

    private void checkLengthCardNumber(String cardNumber, List<String> errors) {
        if (cardNumber.length() != LENGTH_CARD_NUMBER) {
            errors.add(ERROR_LENGTH_CARD);
            if ((cardNumber.isEmpty())) {
                throw new CardValidationException(errors);
            }
        }
    }

    private void checkSymbolsCardNumber(String cardNumber, List<String> errors) {
        for (int i = 0; i <cardNumber.length(); i++) {
            if (Character.isLetter(cardNumber.charAt(i))) {
                errors.add(ERROR_ONLY_DIGITS);
                break;
            }
        }
    }

}
