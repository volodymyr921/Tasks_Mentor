package dev.andrylat.vomelianchuk.finances.dialog;

import dev.andrylat.vomelianchuk.finances.consoleutils.CardNumberValidatorConsoleIO;
import dev.andrylat.vomelianchuk.finances.enums.PaymentSystem;
import dev.andrylat.vomelianchuk.finances.exceptions.CardValidationException;
import dev.andrylat.vomelianchuk.finances.exceptions.WrongPaymentSystemException;
import dev.andrylat.vomelianchuk.finances.card_number_validation.CardNumberValidator;

public class CardNumberValidatorDialog implements Dialog {
    static final String DESCRIPTION_ACTION = "1 - Card Number Validation";

    @Override
    public void start() {
        CardNumberValidatorConsoleIO consoleIO = new CardNumberValidatorConsoleIO(System.in, System.out);
        String cardNumberAsString = consoleIO.read();
        try {
            PaymentSystem paymentSystem = new CardNumberValidator().validate(cardNumberAsString);
            consoleIO.printResult(paymentSystem);
        } catch (CardValidationException | WrongPaymentSystemException e) {
            consoleIO.printErrors(e);
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION_ACTION;
    }

}
