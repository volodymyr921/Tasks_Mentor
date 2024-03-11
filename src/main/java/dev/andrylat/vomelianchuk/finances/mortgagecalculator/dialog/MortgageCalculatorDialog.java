package dev.andrylat.vomelianchuk.finances.mortgagecalculator.dialog;

import dev.andrylat.vomelianchuk.finances.common.Dialog;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.consoleio.MortgageCalculatorConsoleIO;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.exceptions.DataMortgageCalculatorException;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.calculation.MortgageCalculator;

import java.util.List;

public class MortgageCalculatorDialog implements Dialog {
    static final String DESCRIPTION_ACTION = "Mortgage Calculation";

    @Override
    public void start() {
        var consoleIO = new MortgageCalculatorConsoleIO(System.in, System.out);
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
