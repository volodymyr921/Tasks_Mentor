import dev.andrylat.vomelianchuk.CardNumberOperations.CardNumberProcess;
import dev.andrylat.vomelianchuk.CardNumberOperations.enums.Errors;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardNumberErrorValidationTest {
    private final static String descriptionWrongCardNumber = "\nCard number is invalid.\nError:\n";

    String expectedResult;
    String actualResult;

    @Test
    public void shouldErrorNullObjectCardNumber_WhenCardNumberIsNull() {
        expectedResult = new CardNumberProcess(null).operate();
        actualResult = "Error receiving null object instead of card number";

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldErrorLengthCardNumber_WhenCardLengthIsZero() {
        expectedResult = new CardNumberProcess("").operate();
        actualResult = descriptionWrongCardNumber + Errors.ERROR_LENGTH_CARD.getDescription();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldErrorLengthCardNumber_WhenCardLengthIsNot16() {
        expectedResult = new CardNumberProcess("1323 3232").operate();
        actualResult = descriptionWrongCardNumber + Errors.ERROR_LENGTH_CARD.getDescription();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldErrorOnlyDigitsCardNumber_WhenCardNumberContainLetters() {
        expectedResult = new CardNumberProcess("13a3 h342 0s49 23f6").operate();
        actualResult = descriptionWrongCardNumber + Errors.ERROR_ONLY_DIGITS.getDescription();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldErrorDeterminePaymentSystem_WhenCardNumberNotBelongToPaymentSystem() {
        expectedResult = new CardNumberProcess("1323 3342 0149 2326").operate();
        actualResult = descriptionWrongCardNumber + Errors.ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM.getDescription();

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldErrorDeterminePaymentSystem_WhenCardNumberHasIncorrectCheckDigit() {
        expectedResult = new CardNumberProcess("5457 6238 9823 4111").operate();
        actualResult = descriptionWrongCardNumber + Errors.ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM.getDescription();

        assertEquals(expectedResult, actualResult);
    }
}
