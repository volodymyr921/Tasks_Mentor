package dev.andrylat.vomelianchuk.card_number_operations.consoleutils;

import dev.andrylat.vomelianchuk.card_number_operations.enums.PaymentSystem;
import dev.andrylat.vomelianchuk.card_number_operations.exceptions.CardValidationException;
import dev.andrylat.vomelianchuk.card_number_operations.exceptions.DataMortgageCalculatorException;
import dev.andrylat.vomelianchuk.card_number_operations.exceptions.WrongPaymentSystemException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UserConsoleIO {
    private static final String HELLO_USER = "Hello! Enter card number for validation:";
    private static final String VALID_CARD_TEXT_BEGIN =  "\nCard is valid. Payment System is \"";
    private static final String VALID_CARD_TEXT_END =  "\"\n";
    private static final String INVALID_CARD_TEXT_BEGIN =  "\nCard number is invalid.\nError:\n";
    private static final String INVALID_CARD_TEXT_SEPARATOR =  "\n";

    private static final String TEXT_MONTHLY_PAYMENT =  "\nMonthly payment = ";
    private static final String ERROR_BEGIN =  "\nERROR: ";
    private static final String INCORRECT_DATA =  "Data must be numbers";

    private final String[] promptsInput = {"Input total price:", "Input down payment:", "Input  interest rate:", "Input number of years:"};

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

    public List<Double> readDataMortgage() {
        List<Double> listData = new ArrayList<>();
        Scanner scanner = new Scanner(input);
        try {
            for (String prompt : promptsInput) {
                System.out.println(prompt);
                listData.add(scanner.nextDouble());
            }
        } catch (InputMismatchException e) {
            throw new DataMortgageCalculatorException(INCORRECT_DATA);
        }

        return listData;
    }

    private void promptToTheUser() {
        output.println(HELLO_USER);
    }

    public void printPaymentSystem(PaymentSystem paymentSystem) {
        output.println(getPaymentSystemDescription(paymentSystem));
    }

    private String getPaymentSystemDescription(PaymentSystem paymentSystem) {
        return VALID_CARD_TEXT_BEGIN + paymentSystem + VALID_CARD_TEXT_END;
    }

    public void printMonthlyPayment(double monthlyPayment) {
        output.printf(TEXT_MONTHLY_PAYMENT + "%.2f\n", monthlyPayment);
    }

    public void printErrors(RuntimeException e) {
        output.println(getErrorsDescription(e));
    }

    private String getErrorsDescription(RuntimeException e) {
        if (e instanceof CardValidationException) {
            return getErrorsCardInvalid((CardValidationException) e);
        } else if (e instanceof WrongPaymentSystemException){
            return getErrorsWrongPaymentSystem((WrongPaymentSystemException) e);
        } else {
            return getErrorsIncorrectDataMortgage((DataMortgageCalculatorException) e);
        }
    }

    private String getErrorsCardInvalid(CardValidationException e) {
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

    private static String getErrorsIncorrectDataMortgage(DataMortgageCalculatorException e) {
        return ERROR_BEGIN + e.getErrorMessage();
    }

}
