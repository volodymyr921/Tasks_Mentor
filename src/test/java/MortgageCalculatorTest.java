import dev.andrylat.vomelianchuk.card_number_operations.exceptions.DataMortgageCalculatorException;
import dev.andrylat.vomelianchuk.card_number_operations.operations.MortgageCalculator;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MortgageCalculatorTest {

    @ParameterizedTest
    @CsvSource({
            "100000, 20000, 5, 20, 527.9645913733269",
            "150000, 30000, 4, 15, 887.6255107311093",
            "200000, 50000, 3.5, 30, 673.5670317132352",
    })
    public void shouldCalculateMonthlyPayment_WhenDataIsCorrect(double totalPrice, double downPayment, double interestRate, double numberOfYears, double expected) {
        List<Double> dataMortgage = getListDataMortgage(totalPrice, downPayment, interestRate, numberOfYears);

        assertEquals(expected, new MortgageCalculator(dataMortgage).calculateMonthlyPayment());
    }

    @ParameterizedTest
    @CsvSource({
            "0, 20000, 5, 20",
            "150000, -10, 4, 15",
            "200000, 50000, -3.5, -30",
            "-100000, -300000, 4, 15",
            "200000, 5000000, 3.5, 0",
    })
    public void shouldThrowException_WhenDataIsIncorrect(double totalPrice, double downPayment, double interestRate, double numberOfYears) {
        List<Double> dataMortgage = getListDataMortgage(totalPrice, downPayment, interestRate, numberOfYears);
        MortgageCalculator calculator = new MortgageCalculator(dataMortgage);

        assertThrows(DataMortgageCalculatorException.class, calculator::calculateMonthlyPayment);
    }

    private List<Double> getListDataMortgage(double totalPrice, double downPayment, double interestRate, double numberOfYears) {
        List<Double> data = new ArrayList<>();
        data.add(totalPrice);
        data.add(downPayment);
        data.add(interestRate);
        data.add(numberOfYears);

        return data;
    }

}
