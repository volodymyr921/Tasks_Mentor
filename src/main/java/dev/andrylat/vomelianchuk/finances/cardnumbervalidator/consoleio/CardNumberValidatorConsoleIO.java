package dev.andrylat.vomelianchuk.finances.cardnumbervalidator.consoleio;

import dev.andrylat.vomelianchuk.finances.common.ConsoleIO;
import dev.andrylat.vomelianchuk.finances.cardnumbervalidator.enums.PaymentSystem;
import dev.andrylat.vomelianchuk.finances.cardnumbervalidator.exceptions.CardValidationException;
import dev.andrylat.vomelianchuk.finances.cardnumbervalidator.exceptions.WrongPaymentSystemException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class CardNumberValidatorConsoleIO implements ConsoleIO<String> {
    private static final String HELLO_USER = "Hello! Enter card number for validation:";
    private static final String VALID_CARD_TEXT_BEGIN =  "\nCard is valid. Payment System is \"";
    private static final String VALID_CARD_TEXT_END =  "\"\n";
    private static final String INVALID_CARD_TEXT_BEGIN =  "\nCard number is invalid.\nError:\n";
    private static final String INVALID_CARD_TEXT_SEPARATOR =  "\n";
    private static InputStream input;
    private static PrintStream output;

    public CardNumberValidatorConsoleIO(InputStream input, PrintStream output) {
        CardNumberValidatorConsoleIO.input = input;
        CardNumberValidatorConsoleIO.output = output;
    }

    @Override
    public String read() {
        promptToTheUser();
        var scanner = new Scanner(input);
        return scanner.nextLine();
    }

    @Override
    public void printResult(Object paymentSystem) {
        output.println(getPaymentSystemDescription((PaymentSystem) paymentSystem));
    }

    @Override
    public void printErrors(RuntimeException e) {
        output.println(getErrorsDescription(e));
    }

    private void promptToTheUser() {
        output.println(HELLO_USER);
    }

    private String getPaymentSystemDescription(PaymentSystem paymentSystem) {
        return VALID_CARD_TEXT_BEGIN + paymentSystem + VALID_CARD_TEXT_END;
    }

    private String getErrorsDescription(RuntimeException e) {
        if (e instanceof CardValidationException) {
            return getErrorsCardInvalid((CardValidationException) e);
        } else {
            return getErrorsWrongPaymentSystem((WrongPaymentSystemException) e);
        }
    }

    private String getErrorsCardInvalid(CardValidationException e) {
        var descriptionErrors = new StringBuilder(INVALID_CARD_TEXT_BEGIN);

        List<String> errors = e.getErrorsMessage();
        for (String messageError : errors) {
            descriptionErrors.append(messageError).append(INVALID_CARD_TEXT_SEPARATOR);
        }

        return descriptionErrors.toString();
    }

    private static String getErrorsWrongPaymentSystem(WrongPaymentSystemException e) {
        return INVALID_CARD_TEXT_BEGIN + e.getErrorMessage() + INVALID_CARD_TEXT_SEPARATOR;
    }

}
