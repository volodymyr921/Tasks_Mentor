package dev.andrylat.vomelianchuk.enterprise.finances.cardnumbervalidator.dialog;

import dev.andrylat.vomelianchuk.enterprise.finances.cardnumbervalidator.consoleio.CardNumberValidatorConsoleIO;
import dev.andrylat.vomelianchuk.enterprise.finances.cardnumbervalidator.exceptions.CardValidationException;
import dev.andrylat.vomelianchuk.enterprise.finances.cardnumbervalidator.exceptions.WrongPaymentSystemException;
import dev.andrylat.vomelianchuk.enterprise.finances.cardnumbervalidator.validation.CardNumberValidator;
import dev.andrylat.vomelianchuk.enterprise.finances.common.Dialog;

import java.io.InputStream;
import java.io.PrintStream;

public class CardNumberValidatorDialog implements Dialog {
    private static final String DESCRIPTION_ACTION = "Card Number Validation";

    @Override
    public void start(InputStream input, PrintStream output) {
        var consoleIO = new CardNumberValidatorConsoleIO(input, output);
        var cardNumberAsString = consoleIO.read();
        try {
            var paymentSystem = new CardNumberValidator().validate(cardNumberAsString);
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
