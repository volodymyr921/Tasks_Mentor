import dev.andrylat.vomelianchuk.card_number_operations.CardNumberProcess;
import dev.andrylat.vomelianchuk.card_number_operations.ExceptionValidation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardNumberErrorValidationTest {

    @Test
    public void shouldErrorNullObjectCardNumber_WhenCardNumberIsNull() {
        try {
            new CardNumberProcess().determinePaymentSystem(null);
        } catch (ExceptionValidation errors) {
            assertEquals(ExceptionValidation.ERROR_NULL_CARD_NUMBER, errors.getErrorsMessagesInLine());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = "")
    public void shouldErrorsLengthCardNumberAndCannotDeterminePaymentSystem_WhenCardLengthIsZero(String cardNumber) {
        try {
            new CardNumberProcess().determinePaymentSystem(cardNumber);
        } catch (ExceptionValidation errors) {
            assertEquals(ExceptionValidation.ERROR_LENGTH_CARD +
                            ExceptionValidation.ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM,
                    errors.getErrorsMessagesInLine());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"53", "1212", "7148 1216", "6364 2624 2747 3237 1", "4753 4753 2384 2384 2743"})
    public void shouldErrorsLengthCardNumberAndCannotDeterminePaymentSystem_WhenCardLengthIsNot16(String cardNumber) {
        try {
            new CardNumberProcess().determinePaymentSystem(cardNumber);
        } catch (ExceptionValidation errors) {
            assertEquals(ExceptionValidation.ERROR_LENGTH_CARD +
                            ExceptionValidation.ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM,
                    errors.getErrorsMessagesInLine());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"13a3 h342 0s49 23f6", "hf33 84h3 if31 43h2"})
    public void shouldErrorsOnlyDigitsCardNumberAndCannotDeterminePaymentSystem_WhenCardNumberLengthIs16AndContainLetters(String cardNumber) {
        try {
            new CardNumberProcess().determinePaymentSystem(cardNumber);
        } catch (ExceptionValidation errors) {
            assertEquals(ExceptionValidation.ERROR_ONLY_DIGITS +
                            ExceptionValidation.ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM,
                    errors.getErrorsMessagesInLine());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"13a3 h342 23f6", "hf33"})
    public void shouldErrorsLengthCardNumberAndOnlyDigitsCardNumberAndCannotDeterminePaymentSystem_WhenCardNumberLengthIsNot16AndContainLetters(String cardNumber) {
        try {
            new CardNumberProcess().determinePaymentSystem(cardNumber);
        } catch (ExceptionValidation errors) {
            assertEquals(ExceptionValidation.ERROR_LENGTH_CARD +
                            ExceptionValidation.ERROR_ONLY_DIGITS +
                            ExceptionValidation.ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM,
                    errors.getErrorsMessagesInLine());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1323 1342 2336 2322", "4333 1332 6534 1233", "1332 8564 7348 3643", "6012 3424 2347 7263", "4384 7247 7345 7234"})
    public void shouldErrorCannotDeterminePaymentSystem_WhenCardNumberNotBelongToPaymentSystemOrHasIncorrectCheckDigit(String cardNumber) {
        try {
            new CardNumberProcess().determinePaymentSystem(cardNumber);
        } catch (ExceptionValidation errors) {
            assertEquals(ExceptionValidation.ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM, errors.getErrorsMessagesInLine());
        }
    }

}
