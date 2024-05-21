package dev.andrylat.vomelianchuk.finances.cardnumbervalidator.validation;

import dev.andrylat.vomelianchuk.finances.cardnumbervalidator.exceptions.CardValidationException;

import java.util.List;

public class LuhnAlgorithm {
    private static final String ERROR_INCORRECT_LUHN_NUMBER = "Incorrect Luhn checksum";

    public void checkLastNumber(String cardNumber, List<String> errors) {
        int firstNumber = calculateFirstNumber(cardNumber);
        int secondNumber = calculateSecondNumber(cardNumber);
        int lastDigit = calculateLastDigit(firstNumber, secondNumber);

        if (!(isValidLastDigit(cardNumber, lastDigit))) {
            errors.add(ERROR_INCORRECT_LUHN_NUMBER);
            throw new CardValidationException(errors);
        }
    }

    private int calculateFirstNumber(String cardNumber) {
        int sum = 0;

        for (int i = 0; i < cardNumber.length() - 1; i += 2) {
            int digit = calculateDigit(cardNumber.charAt(i));
            sum += digit;
        }

        return sum;
    }

    private int calculateSecondNumber(String cardNumber) {
        int sum = 0;

        for (int i = 1; i < cardNumber.length() - 1; i += 2) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            sum += digit;
        }

        return sum;
    }

    private int calculateLastDigit(int firstNumber, int secondNumber) {
        int sumNumbers = firstNumber + secondNumber;
        int remainder = amountsOfUnits(sumNumbers);

        return 10 - remainder;
    }

    private int calculateDigit(char digitChar) {
        int number = Character.getNumericValue(digitChar) * 2;

        if (isNotSingleDigitNumber(number)) {
            return amountsOfUnits(number) + 1;
        } else {
            return number;
        }
    }

    private boolean isNotSingleDigitNumber(int number) {
        return number >= 10;
    }

    private int amountsOfUnits(int number) {
        return number % 10;
    }

    private boolean isValidLastDigit(String cardNumber, int lastDigit) {
        return lastDigit == Character.getNumericValue(cardNumber.charAt(cardNumber.length() - 1));
    }

}
