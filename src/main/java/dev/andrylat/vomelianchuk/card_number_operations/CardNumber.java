package dev.andrylat.vomelianchuk.card_number_operations;

import dev.andrylat.vomelianchuk.card_number_operations.consoleutils.UserConsoleIO;
import dev.andrylat.vomelianchuk.card_number_operations.enums.PaymentSystem;
import dev.andrylat.vomelianchuk.card_number_operations.exceptions.CardValidationException;
import dev.andrylat.vomelianchuk.card_number_operations.exceptions.DataMortgageCalculatorException;
import dev.andrylat.vomelianchuk.card_number_operations.exceptions.WrongPaymentSystemException;
import dev.andrylat.vomelianchuk.card_number_operations.operations.MortgageCalculator;
import dev.andrylat.vomelianchuk.card_number_operations.operations.validation.CardNumberValidator;

import java.util.List;

public class CardNumber {
    public static void main(String[] args) {
            UserConsoleIO userConsoleIO = new UserConsoleIO(System.in, System.out);
            String cardNumberAsString = userConsoleIO.readCardNumber();
            try {
                CardNumberValidator cardNumberValidator = new CardNumberValidator();
                PaymentSystem paymentSystem = cardNumberValidator.validate(cardNumberAsString);
                userConsoleIO.printPaymentSystem(paymentSystem);

                List<Double> dataMortgage = userConsoleIO.readDataMortgage();
                double monthlyPayment = new MortgageCalculator(dataMortgage).calculateMonthlyPayment();
                userConsoleIO.printMonthlyPayment(monthlyPayment);
            } catch (CardValidationException | WrongPaymentSystemException | DataMortgageCalculatorException e) {
                userConsoleIO.printErrors(e);
            }
    }

}
