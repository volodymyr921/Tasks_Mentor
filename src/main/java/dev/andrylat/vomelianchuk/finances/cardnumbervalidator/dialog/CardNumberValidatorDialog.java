package dev.andrylat.vomelianchuk.finances.cardnumbervalidator.dialog;

import dev.andrylat.vomelianchuk.finances.common.Dialog;
import dev.andrylat.vomelianchuk.finances.cardnumbervalidator.consoleio.CardNumberValidatorConsoleIO;
import dev.andrylat.vomelianchuk.finances.cardnumbervalidator.exceptions.CardValidationException;
import dev.andrylat.vomelianchuk.finances.cardnumbervalidator.exceptions.WrongPaymentSystemException;
import dev.andrylat.vomelianchuk.finances.cardnumbervalidator.validation.CardNumberValidator;

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
