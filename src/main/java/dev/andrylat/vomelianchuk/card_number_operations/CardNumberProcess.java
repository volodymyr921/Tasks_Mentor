package dev.andrylat.vomelianchuk.card_number_operations;

import java.util.ArrayList;
import java.util.Optional;

public class CardNumberProcess {
    private static final int LENGTH_CARD_NUMBER = 16;
    private static final String HIATUS_SEPARATOR = "\\s";

    private PaymentSystem paymentSystem;
    private ExceptionValidation errors = new ExceptionValidation(new ArrayList<>());

    public PaymentSystem determinePaymentSystem(String cardNumber) throws ExceptionValidation {
        if (cardNumber != null) {
            cardNumber = transform(cardNumber);
            if (validate(cardNumber)) {
                determine(cardNumber);
            } else {
                throw new ExceptionValidation(errors.getErrorsMessage());
            }
            return paymentSystem;
        } else {
            throw new ExceptionValidation(ExceptionValidation.ERROR_NULL_CARD_NUMBER, errors.getErrorsMessage());
        }
    }

    private String transform(String cardNumber) {
        return cardNumber.replaceAll(HIATUS_SEPARATOR, "");
    }

    private boolean validate(String cardNumber) throws ExceptionValidation {
        return checkLengthCardNumber(cardNumber) &
                checkSymbolsCardNumber(cardNumber) &
                new LuhnAlgorithm(cardNumber).checkDigit(errors);
    }

    private boolean checkLengthCardNumber(String cardNumber) {
        if (cardNumber.length() != LENGTH_CARD_NUMBER) {
            errors = new ExceptionValidation(ExceptionValidation.ERROR_LENGTH_CARD, errors.getErrorsMessage());
            return false;
        }
        return true;
    }

    private boolean checkSymbolsCardNumber(String cardNumber) {
        int i = 0;
        while (i < cardNumber.length()) {
            if (Character.isLetter(cardNumber.charAt(i))) {
                errors = new ExceptionValidation(ExceptionValidation.ERROR_ONLY_DIGITS, errors.getErrorsMessage());
                return false;
            }
            i++;
        }
        return true;
    }

    private void determine(String cardNumber) throws ExceptionValidation {
        Optional<PaymentSystem> paymentSystemOptional = PaymentSystem.detectPaymentSystem(cardNumber);
        if (paymentSystemOptional.isPresent()) {
            paymentSystem = paymentSystemOptional.get();
        } else {
            throw new ExceptionValidation(ExceptionValidation.ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM, errors.getErrorsMessage());
        }
    }

}
