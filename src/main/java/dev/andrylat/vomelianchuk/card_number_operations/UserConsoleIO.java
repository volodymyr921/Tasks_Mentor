package dev.andrylat.vomelianchuk.card_number_operations;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class UserConsoleIO {
    private static final String HELLO_USER = "Hello! Enter card number for validation:";
    private static final String VALID_CARD_TEXT_BEGIN =  "\nCard is valid. Payment System is \"";
    private static final String VALID_CARD_TEXT_END =  "\"";
    private static final String INVALID_CARD_TEXT_BEGIN =  "\nCard number is invalid.\nError:\n";
    private static final String INVALID_CARD_TEXT_SEPARATOR =  "\n";

    private static InputStream input;
    private static PrintStream output;

    public UserConsoleIO(InputStream input, PrintStream output) {
        UserConsoleIO.input = input;
        UserConsoleIO.output = output;
    }

    public String readCardNumber() {
        promptToTheUser();
        Scanner scanner = new Scanner(input);
        return scanner.nextLine();
    }

    private void promptToTheUser() {
        output.println(HELLO_USER);
    }

    public static void printPaymentSystem(PaymentSystem paymentSystem) {
        output.println(getPaymentSystemDescription(paymentSystem));
    }

    private static String getPaymentSystemDescription(PaymentSystem paymentSystem) {
        return VALID_CARD_TEXT_BEGIN + paymentSystem + VALID_CARD_TEXT_END;
    }

    public static void printErrors(RuntimeException e) {
        output.println(getErrorsDescription(e));
    }

    private static String getErrorsDescription(RuntimeException e) {
        if (e instanceof CardValidationException) {
            return getErrorsCardInvalid((CardValidationException) e);
        } else {
            return getErrorsWrongPaymentSystem((WrongPaymentSystemException) e);
        }
    }

    private static String getErrorsCardInvalid(CardValidationException e) {
        StringBuilder descriptionErrors = new StringBuilder(INVALID_CARD_TEXT_BEGIN);

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
