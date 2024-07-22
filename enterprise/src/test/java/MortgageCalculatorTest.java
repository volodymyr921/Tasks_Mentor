import dev.andrylat.vomelianchuk.enterprise.finances.mortgagecalculator.calculation.MortgageCalculator;
import dev.andrylat.vomelianchuk.enterprise.finances.mortgagecalculator.dto.MortgageCalculatorDTO;
import dev.andrylat.vomelianchuk.enterprise.finances.mortgagecalculator.exceptions.DataMortgageCalculatorException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MortgageCalculatorTest {

    @ParameterizedTest
    @MethodSource("provideValidData")
    public void shouldCalculateMonthlyPayment_WhenDataIsCorrect(double totalPrice, double downPayment, double interestRate, double numberOfYears, double expected) {
        List<Double> dataMortgage = getListDataMortgage(totalPrice, downPayment, interestRate, numberOfYears);
        MortgageCalculatorDTO mortgageDTO = new MortgageCalculatorDTO(dataMortgage);
        assertEquals(expected, new MortgageCalculator(mortgageDTO).calculateMonthlyPayment(), 0.0001);
    }

    @ParameterizedTest
    @MethodSource("provideInvalidData")
    public void shouldThrowException_WhenDataIsIncorrect(double totalPrice, double downPayment, double interestRate, double numberOfYears) {
        List<Double> dataMortgage = getListDataMortgage(totalPrice, downPayment, interestRate, numberOfYears);
        MortgageCalculatorDTO mortgageDTO = new MortgageCalculatorDTO(dataMortgage);
        MortgageCalculator calculator = new MortgageCalculator(mortgageDTO);

        assertThrows(DataMortgageCalculatorException.class, calculator::calculateMonthlyPayment);
    }

    private static Stream<Arguments> provideValidData() {
        return Stream.of(
                Arguments.of(100000, 20000, 5, 20, 527.9645913733269),
                Arguments.of(150000, 30000, 4, 15, 887.6255107311093),
                Arguments.of(200000, 50000, 3.5, 30, 673.5670317132352)
        );
    }

    private static Stream<Arguments> provideInvalidData() {
        return Stream.of(
                Arguments.of(0, 20000, 5, 20),
                Arguments.of(150000, -10, 4, 15),
                Arguments.of(200000, 50000, -3.5, -30),
                Arguments.of(-100000, -300000, 4, 15),
                Arguments.of(200000, 5000000, 3.5, 0)
        );
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
