package dev.andrylat.vomelianchuk.card_number_operations;

import dev.andrylat.vomelianchuk.card_number_operations.consoleutils.UserConsoleIO;
import dev.andrylat.vomelianchuk.card_number_operations.enums.PaymentSystem;
import dev.andrylat.vomelianchuk.card_number_operations.exceptions.CardValidationException;
import dev.andrylat.vomelianchuk.card_number_operations.exceptions.WrongPaymentSystemException;
import dev.andrylat.vomelianchuk.card_number_operations.validation.CardNumberValidator;

public class CardNumber {
    public static void main(String[] args) {
            UserConsoleIO userConsoleIO = new UserConsoleIO(System.in, System.out);
            String cardNumberAsString = userConsoleIO.readCardNumber();
            try {
                CardNumberValidator cardNumberValidator = new CardNumberValidator();
                PaymentSystem paymentSystem = cardNumberValidator.validate(cardNumberAsString);
                userConsoleIO.printPaymentSystem(paymentSystem);
            } catch (CardValidationException | WrongPaymentSystemException e) {
                userConsoleIO.printErrors(e);
            }
    }

}
