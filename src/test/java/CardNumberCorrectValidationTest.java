import dev.andrylat.vomelianchuk.card_number_operations.PaymentSystem;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardNumberCorrectValidationTest {

    @ParameterizedTest
    @ValueSource(strings = {"4442 1144 6253 6845"})
    public void shouldVisaPaymentSystem_WhenCardNumberIsCorrectAndStarsWith4(String cardNumber) {
        assertEquals(PaymentSystem.VISA, PaymentSystem.determine(cardNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"5157 6238 9823 4116", "5257 6238 9823 4115", "5357 6238 9823 4114", "5457 6238 9823 4113", "5557 6238 9823 4112"})
    public void shouldMastercardPaymentSystem_WhenCardNumberIsCorrectAndStarsFrom51To55(String cardNumber) {
        assertEquals(PaymentSystem.MASTERCARD, PaymentSystem.determine(cardNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"3642 1144 6253 6845", "3842 1144 6253 6843"})
    public void shouldDinersClubPaymentSystem_WhenCardNumberIsCorrectAndStarsWith36or38(String cardNumber) {
        assertEquals(PaymentSystem.DINERS_CLUB, PaymentSystem.determine(cardNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"6011 1144 6253 6841", "6542 1144 6253 6849"})
    public void shouldDiscoverPaymentSystem_WhenCardNumberIsCorrectAndStarsWith6011or65(String cardNumber) {
        assertEquals(PaymentSystem.DISCOVER, PaymentSystem.determine(cardNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"3542 1144 6253 6846"})
    public void shouldJcbPaymentSystem_WhenCardNumberIsCorrectAndStarsWith35(String cardNumber) {
        assertEquals(PaymentSystem.JCB, PaymentSystem.determine(cardNumber));
    }

    @ParameterizedTest
    @ValueSource(strings = {"3442 1144 6253 6847", "3742 1144 6253 6844"})
    public void shouldAmericanExpressPaymentSystem_WhenCardNumberIsCorrectAndStarsWith34or37(String cardNumber) {
        assertEquals(PaymentSystem.AMERICAN_EXPRESS, PaymentSystem.determine(cardNumber));
    }

}
