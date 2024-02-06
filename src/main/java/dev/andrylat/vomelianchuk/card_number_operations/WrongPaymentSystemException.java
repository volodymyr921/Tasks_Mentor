package dev.andrylat.vomelianchuk.card_number_operations;

public class WrongPaymentSystemException extends RuntimeException {
    private final String errorMessage;

    public WrongPaymentSystemException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
