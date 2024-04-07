package dev.andrylat.vomelianchuk.finances.mortgagecalculator.dialog;

import dev.andrylat.vomelianchuk.finances.common.Dialog;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.dto.MortgageCalculatorDTO;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.consoleio.MortgageCalculatorConsoleIO;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.exceptions.DataMortgageCalculatorException;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.calculation.MortgageCalculator;

import java.util.List;

public class MortgageCalculatorDialog implements Dialog {
    private static final String DESCRIPTION_ACTION = "Mortgage Calculation";

    @Override
    public void start() {
        var consoleIO = new MortgageCalculatorConsoleIO(input, output);
        try {
            List<Double> dataMortgage = consoleIO.read();
            MortgageCalculatorDTO mortgageDTO = new MortgageCalculatorDTO(dataMortgage);
            double monthlyPayment = new MortgageCalculator(mortgageDTO).calculateMonthlyPayment();
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
