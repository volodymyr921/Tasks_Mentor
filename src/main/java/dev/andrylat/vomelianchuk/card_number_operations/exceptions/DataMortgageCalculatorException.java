package dev.andrylat.vomelianchuk.card_number_operations.exceptions;

public class DataMortgageCalculatorException extends RuntimeException{
    private final String errorMessage;

    public DataMortgageCalculatorException(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
