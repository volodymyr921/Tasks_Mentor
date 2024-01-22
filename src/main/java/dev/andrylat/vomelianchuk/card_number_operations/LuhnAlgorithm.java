package dev.andrylat.vomelianchuk.card_number_operations;

public class LuhnAlgorithm {
    private final String cardNumber;

    public LuhnAlgorithm(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public boolean checkDigit(ExceptionValidation errors) throws ExceptionValidation {
        if (cardNumber.isEmpty()) {
            throw new ExceptionValidation(ExceptionValidation.ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM, errors.getErrorsMessage());
        }

        int firstNumber = calculateFirstNumber();
        int secondNumber = calculateSecondNumber();
        int lastDigit = calculateLastDigit(firstNumber, secondNumber);

        if (lastDigit == Character.getNumericValue(cardNumber.charAt(cardNumber.length() - 1))) {
            return true;
        } else {
            throw new ExceptionValidation(ExceptionValidation.ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM, errors.getErrorsMessage());
        }
    }

    private int calculateFirstNumber() {
        int sum = 0;

        for (int i = 0; i < cardNumber.length() - 1; i += 2) {
            int digit = calculateDigit(cardNumber.charAt(i));
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

    private int calculateLastDigit(int firstNumber, int secondNumber) {
        int sumNumbers = firstNumber + secondNumber;
        int remainder = amountsOfUnits(sumNumbers);

        return 10 - remainder;
    }



    private int calculateDigit(char digitChar) {
        int number = Character.getNumericValue(digitChar) * 2;

        if (isTwoDigitNumber(number)) {
            return amountsOfUnits(number) + 1;
        } else {
            return number;
        }
    }

    private boolean isTwoDigitNumber(int number) {
        return number >= 10;
    }

    private int amountsOfUnits(int number) {
        return number % 10;
    }

}
