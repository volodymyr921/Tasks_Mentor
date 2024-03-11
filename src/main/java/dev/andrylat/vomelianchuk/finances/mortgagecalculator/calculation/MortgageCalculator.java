package dev.andrylat.vomelianchuk.finances.mortgagecalculator.calculation;

import dev.andrylat.vomelianchuk.finances.mortgagecalculator.exceptions.DataMortgageCalculatorException;

import java.util.List;

public class MortgageCalculator {
    private static final String INCORRECT_NUMBERS =  "The numbers are not correct";
    private final static double paymentsPerYear = 12;
    private final double totalPrice;
    private final double downPayment;
    private double interestRate;
    private final double numberOfYears;

    public MortgageCalculator(List<Double> dataMortgage) {
        totalPrice = dataMortgage.get(0);
        downPayment = dataMortgage.get(1);
        interestRate = dataMortgage.get(2);
        numberOfYears = dataMortgage.get(3);
    }

    public double calculateMonthlyPayment() {
        checkCorrectnessData();
        double loanAmount = totalPrice - downPayment;
        double numberOfMonths = numberOfYears * paymentsPerYear;
        interestRate /= 100;

        return loanAmount * (interestRate / paymentsPerYear) * Math.pow((1 + interestRate / paymentsPerYear), numberOfMonths) /
                (Math.pow((1 + interestRate / paymentsPerYear), numberOfMonths) - 1);
    }

    private void checkCorrectnessData() {
        if (totalPrice < 0 || downPayment < 0 || interestRate < 0 || numberOfYears <= 0 || (totalPrice - downPayment) < 0) {
            throw new DataMortgageCalculatorException(INCORRECT_NUMBERS);
        }
    }

}
