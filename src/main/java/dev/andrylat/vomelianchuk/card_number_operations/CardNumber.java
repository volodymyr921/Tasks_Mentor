package dev.andrylat.vomelianchuk.card_number_operations;

import java.util.List;

public class CardNumber {
    public static void main(String[] args) {
        UserConsoleIO userConsoleIO = new UserConsoleIO();
        String cardNumber = userConsoleIO.readCardNumber();

        validateCardNumberAndDisplayResult(cardNumber);
    }

    private static void validateCardNumberAndDisplayResult(String cardNumber) {
        CardNumberProcess card = new CardNumberProcess();

        try {
            PaymentSystem paymentSystem = card.determinePaymentSystem(cardNumber);
            System.out.println(getDescriptionPaymentSystem(paymentSystem));
        } catch (ExceptionValidation exceptionValidation) {
            System.out.println(getDescriptionErrors(exceptionValidation));
        }
    }

    private static String getDescriptionPaymentSystem(PaymentSystem paymentSystem) {
        return "\nCard is valid. Payment System is \"" + paymentSystem + "\"";
    }

    private static String getDescriptionErrors(ExceptionValidation exceptionValidation) {
        StringBuilder descriptionErrors = new StringBuilder("\nCard number is invalid.\nError:\n");

        List<String> errors = exceptionValidation.getErrorsMessage();
        for (String messageError : errors) {
            descriptionErrors.append("-> ").append(messageError).append("\n");
        }

        return descriptionErrors.toString();
    }

}
