import dev.andrylat.vomelianchuk.card_number_operations.CardNumberValidator;
import dev.andrylat.vomelianchuk.card_number_operations.CardValidationException;

import dev.andrylat.vomelianchuk.card_number_operations.PaymentSystem;
import dev.andrylat.vomelianchuk.card_number_operations.WrongPaymentSystemException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardNumberErrorValidationTest {
    private static final String ERROR_NULL_CARD_NUMBER = "Error receiving null object instead of card number";
    private static final String ERROR_LENGTH_CARD = "Length should be 16 symbols";
    private static final String ERROR_ONLY_DIGITS = "Number should contain only digits";
    private static final String ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM = "Payment System can't be determine";

    @Test
    public void shouldErrorNullObjectCardNumber_WhenCardNumberIsNull() {
        try {
            new CardNumberValidator().validate(null);
        } catch (CardValidationException errors) {
            assertEquals(ERROR_NULL_CARD_NUMBER, errors.getErrorsMessagesInLine());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = "")
    public void shouldErrorsLengthCardNumberAndCannotDeterminePaymentSystem_WhenCardLengthIsZero(String cardNumber) {
        try {
            new CardNumberValidator().validate(cardNumber);
        } catch (CardValidationException errors) {
            assertEquals(ERROR_LENGTH_CARD,
                    errors.getErrorsMessagesInLine());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"53", "1212", "7148 1216", "6364 2624 2747 3237 1", "4753 4753 2384 2384 2743"})
    public void shouldErrorsLengthCardNumberAndCannotDeterminePaymentSystem_WhenCardLengthIsNot16(String cardNumber) {
        try {
            new CardNumberValidator().validate(cardNumber);
        } catch (CardValidationException errors) {
            assertEquals(ERROR_LENGTH_CARD +
                            ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM,
                    errors.getErrorsMessagesInLine());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"13a3 h342 0s49 23f6", "hf33 84h3 if31 43h2"})
    public void shouldErrorsOnlyDigitsCardNumberAndCannotDeterminePaymentSystem_WhenCardNumberLengthIs16AndContainLetters(String cardNumber) {
        try {
            new CardNumberValidator().validate(cardNumber);
        } catch (CardValidationException errors) {
            assertEquals(ERROR_ONLY_DIGITS +
                            ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM,
                    errors.getErrorsMessagesInLine());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"13a3 h342 23f6", "hf33"})
    public void shouldErrorsLengthCardNumberAndOnlyDigitsCardNumberAndCannotDeterminePaymentSystem_WhenCardNumberLengthIsNot16AndContainLetters(String cardNumber) {
        try {
            new CardNumberValidator().validate(cardNumber);
        } catch (CardValidationException errors) {
            assertEquals(ERROR_LENGTH_CARD +
                            ERROR_ONLY_DIGITS +
                            ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM,
                    errors.getErrorsMessagesInLine());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"5323 1342 2336 2322", "4333 1332 6534 1233", "4332 8564 7348 3643", "6011 3424 2347 7263", "4384 7247 7345 7234"})
    public void shouldErrorCannotDeterminePaymentSystem_WhenCardNumberHasIncorrectCheckDigit(String cardNumber) {
        try {
            new CardNumberValidator().validate(cardNumber);
        } catch (CardValidationException errors) {
            assertEquals(ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM, errors.getErrorsMessagesInLine());
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"1323 1342 2336 2322", "2333 1332 6534 1233", "1332 8564 7348 3643", "6012 3424 2347 7263", "9384 7247 7345 7234"})
    public void shouldErrorCannotDeterminePaymentSystem_WhenCardNumberNotBelongToPaymentSystem(String cardNumber) {
        try {
            PaymentSystem.determine(cardNumber);
        } catch (WrongPaymentSystemException error) {
            assertEquals(ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM, error.getErrorMessage());
        }
    }

}
