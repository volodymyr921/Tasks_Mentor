package dev.andrylat.vomelianchuk.card_number_operations.exceptions;

import java.util.List;

public class CardValidationException extends RuntimeException {
    private final List<String> errorsMessage;

    public CardValidationException(List<String> errorsMessage) {
        this.errorsMessage = errorsMessage;
    }

    public List<String> getErrorsMessage() {
        return errorsMessage;
    }

}
