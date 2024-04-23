package dev.andrylat.vomelianchuk.finances.mortgagecalculator.dialog;

import dev.andrylat.vomelianchuk.finances.common.Dialog;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.dto.MortgageCalculatorDTO;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.consoleio.MortgageCalculatorConsoleIO;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.exceptions.DataMortgageCalculatorException;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.calculation.MortgageCalculator;

import java.io.InputStream;
import java.io.PrintStream;

public class MortgageCalculatorDialog implements Dialog {
    private static final String DESCRIPTION_ACTION = "Mortgage Calculation";

    @Override
    public void start(InputStream input, PrintStream output) {
        var consoleIO = new MortgageCalculatorConsoleIO(input, output);
        try {
            var dataMortgage = consoleIO.read();
            var mortgageDTO = new MortgageCalculatorDTO(dataMortgage);
            var monthlyPayment = new MortgageCalculator(mortgageDTO).calculateMonthlyPayment();
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
