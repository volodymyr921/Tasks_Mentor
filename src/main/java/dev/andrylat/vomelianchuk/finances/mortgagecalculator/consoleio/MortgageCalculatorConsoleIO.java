package dev.andrylat.vomelianchuk.finances.mortgagecalculator.consoleio;

import dev.andrylat.vomelianchuk.finances.common.ConsoleIO;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.exceptions.DataMortgageCalculatorException;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class MortgageCalculatorConsoleIO implements ConsoleIO<List<Double>> {
    private static final String TEXT_MONTHLY_PAYMENT =  "\nMonthly payment = ";
    private static final String ERROR_BEGIN =  "\nERROR: ";
    private static final String INCORRECT_DATA =  "Data must be numbers";

    private final String[] promptsInput = {"Input total price:", "Input down payment:", "Input  interest rate:", "Input number of years:"};

    private final InputStream input;
    private final PrintStream output;

    public MortgageCalculatorConsoleIO(InputStream input, PrintStream output) {
        this.input = input;
        this.output = output;
    }

    @Override
    public List<Double> read() {
        List<Double> listData = new ArrayList<>();
        var scanner = new Scanner(input);
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

    @Override
    public void printResult(Object monthlyPayment) {
        output.printf(TEXT_MONTHLY_PAYMENT + "%.2f\n",(Double) monthlyPayment);
    }

    @Override
    public void printErrors(RuntimeException e) {
        output.println(getErrorsDescription((DataMortgageCalculatorException) e));
    }

    private String getErrorsDescription(DataMortgageCalculatorException e) {
        return ERROR_BEGIN + e.getErrorMessage();
    }

}
