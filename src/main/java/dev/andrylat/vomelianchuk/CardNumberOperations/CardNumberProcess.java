package dev.andrylat.vomelianchuk.CardNumberOperations;

import dev.andrylat.vomelianchuk.CardNumberOperations.enums.Errors;
import dev.andrylat.vomelianchuk.CardNumberOperations.enums.PaymentSystem;

import java.util.Optional;

public class CardNumberProcess {
    private static final int LENGTH_CARD_NUMBER = 16;
    private static final String HIATUS_SEPARATOR = "\\s";
    private static final int TEN = 10;

    private String cardNumber;
    private PaymentSystem paymentSystem;
    private Errors error;

    public CardNumberProcess(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String operate() {
        try {
            cardNumber = transform();
            if (validate()) {
                determine();
            }
            return messageResult();
        } catch (NullPointerException exception) {
            return "Error receiving null object instead of card number";
        }
    }

    private String transform() throws NullPointerException {
        return cardNumber.replaceAll(HIATUS_SEPARATOR, "");
    }

    private boolean validate() {
        return checkLengthCardNumber() &&
                checkSymbolsCardNumber() &&
                checkDigitByLuhnAlgorithm();
    }

    private boolean checkSymbolsCardNumber() {
        int i = 0;
        while (i < cardNumber.length()) {
            if (Character.isLetter(cardNumber.charAt(i))) {
                error = Errors.ERROR_ONLY_DIGITS;
                return false;
            }
            i++;
        }
        return true;
    }

    private boolean checkLengthCardNumber() {
        if (cardNumber.length() != LENGTH_CARD_NUMBER) {
            error = Errors.ERROR_LENGTH_CARD;
            return false;
        }
        return true;
    }

    private boolean checkDigitByLuhnAlgorithm() {
        int firstNumber = calculateFirstNumber();
        int secondNumber = calculateSecondNumber();

        int sumNumbers = firstNumber + secondNumber;
        int diff = sumNumbers % TEN;
        int lastDigit = TEN - diff;

        if (lastDigit != Character.getNumericValue(cardNumber.charAt(cardNumber.length() - 1))) {
            error = Errors.ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM;
            return false;
        }

        return true;
    }

    private int calculateFirstNumber() {
        int sum = 0;
        for (int i = 0; i < cardNumber.length() - 1; i += 2) {
            int digit= calculateDigit(cardNumber.charAt(i));
            sum += digit;
        }
        return sum;
    }

    private int calculateSecondNumber() {
        int sum = 0;
        for (int i = 1; i < cardNumber.length() - 1; i += 2) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            sum += digit;
        }
        return sum;
    }

    private int calculateDigit(char digitChar) {
        int number = Character.getNumericValue(digitChar) * 2;
        return (number >= TEN) ? (number % TEN) + 1 : number;
    }

    private void determine() {
        Optional<PaymentSystem> paymentSystemOptional = PaymentSystem.detectPaymentSystem(cardNumber);
        if (paymentSystemOptional.isPresent()) {
            paymentSystem = paymentSystemOptional.get();
        } else {
            error = Errors.ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM;
        }
    }

    private String messageResult() {
        if (paymentSystem != null) {
            return "\nCard is valid. Payment System is " + paymentSystem;
        } else {
            return "\nCard number is invalid.\nError:\n" + error.getDescription();
        }
    }
}
