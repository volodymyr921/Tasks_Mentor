package dev.andrylat.vomelianchuk.CardNumberOperations.enums;

public enum Errors {
    ERROR_LENGTH_CARD("Length should be 16 symbols"),
    ERROR_ONLY_DIGITS("Number should contain only digits"),
    ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM("Payment System can't be determined");

    private final String description;

    Errors(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
