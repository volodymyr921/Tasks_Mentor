package dev.andrylat.vomelianchuk.finances.mortgagecalculator.calculation;

import dev.andrylat.vomelianchuk.finances.mortgagecalculator.dto.MortgageCalculatorDTO;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.exceptions.DataMortgageCalculatorException;

public class MortgageCalculator {
    private static final String INCORRECT_NUMBERS =  "The numbers are not correct";
    private final static double paymentsPerYear = 12;
    private final double totalPrice;
    private final double downPayment;
    private double interestRate;
    private final double numberOfYears;

    public MortgageCalculator(MortgageCalculatorDTO calculatorDTO) {
        this.totalPrice = calculatorDTO.getTotalPrice();
        this.downPayment = calculatorDTO.getDownPayment();
        this.interestRate = calculatorDTO.getInterestRate();
        this.numberOfYears = calculatorDTO.getNumberOfYears();
    }

    public double calculateMonthlyPayment() {
        checkCorrectnessData();
        var loanAmount = totalPrice - downPayment;
        var numberOfMonths = numberOfYears * paymentsPerYear;
        interestRate /= 100;

        var monthlyInterestRate = interestRate / paymentsPerYear;
        var compoundFactor = Math.pow((1 + monthlyInterestRate), numberOfMonths);

        return loanAmount * (monthlyInterestRate * compoundFactor) / (compoundFactor - 1);
    }

    private void checkCorrectnessData() {
        if (totalPrice < 0 || downPayment < 0 || interestRate < 0 || numberOfYears <= 0 || (totalPrice - downPayment) < 0) {
            throw new DataMortgageCalculatorException(INCORRECT_NUMBERS);
        }
    }

}
