package dev.andrylat.vomelianchuk.card_number_operations;

import java.util.List;

public class ExceptionValidation extends RuntimeException {
    public static final String ERROR_NULL_CARD_NUMBER = "Error receiving null object instead of card number";
    public static final String ERROR_LENGTH_CARD = "Length should be 16 symbols";
    public static final String ERROR_ONLY_DIGITS = "Number should contain only digits";
    public static final String ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM = "Payment System can't be determine";
    private final List<String> errorsMessage;

    public ExceptionValidation(List<String> errorsMessage) {
        this.errorsMessage = errorsMessage;
    }

    public ExceptionValidation(String message, List<String> errorsMessage) {
        this.errorsMessage = errorsMessage;
        errorsMessage.add(message);
    }

    public List<String> getErrorsMessage() {
        return errorsMessage;
    }

    public String getErrorsMessagesInLine() {
        StringBuilder errorsLine = new StringBuilder();
        for (String error : errorsMessage) {
            errorsLine.append(error);
        }
        return errorsLine.toString();
    }

}
