package dev.andrylat.vomelianchuk.finances.dialog;

import dev.andrylat.vomelianchuk.finances.consoleutils.MortgageCalculatorConsoleIO;
import dev.andrylat.vomelianchuk.finances.exceptions.DataMortgageCalculatorException;
import dev.andrylat.vomelianchuk.finances.operations.MortgageCalculator;

import java.util.List;

public class MortgageCalculatorDialog implements Dialog {
    static final String DESCRIPTION_ACTION = "2 - Mortgage Calculation";

    @Override
    public void start() {
        MortgageCalculatorConsoleIO consoleIO = new MortgageCalculatorConsoleIO(System.in, System.out);
        try {
            List<Double> dataMortgage = consoleIO.read();
            double monthlyPayment = new MortgageCalculator(dataMortgage).calculateMonthlyPayment();
            consoleIO.printResult(monthlyPayment);
        } catch (DataMortgageCalculatorException e) {
            consoleIO.printErrors(e);
        }
    }

    @Override
    public String getDescription() {
        return DESCRIPTION_ACTION;
    }

}
