import dev.andrylat.vomelianchuk.CardNumberOperations.CardNumberProcess;
import dev.andrylat.vomelianchuk.CardNumberOperations.enums.PaymentSystem;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CardNumberCorrectValidationTest {
    private final static String descriptionCorrectCardNumber = "\nCard is valid. Payment System is ";

    String expectedResult;
    String actualResult;

    @Test
    public void shouldDescriptionVisaPaymentSystem_WhenCardNumberIsCorrectAndStarsWith4() {
        expectedResult = new CardNumberProcess("4442 1144 6253 6845").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.VISA;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldDescriptionMastercardPaymentSystem_WhenCardNumberIsCorrectAndStarsWith51() {
        expectedResult = new CardNumberProcess("5157 6238 9823 4116").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.MASTERCARD;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldDescriptionMastercardPaymentSystem_WhenCardNumberIsCorrectAndStarsWith52() {
        expectedResult = new CardNumberProcess("5257 6238 9823 4115").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.MASTERCARD;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldDescriptionMastercardPaymentSystem_WhenCardNumberIsCorrectAndStarsWith53() {
        expectedResult = new CardNumberProcess("5357 6238 9823 4114").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.MASTERCARD;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldDescriptionMastercardPaymentSystem_WhenCardNumberIsCorrectAndStarsWith54() {
        expectedResult = new CardNumberProcess("5457 6238 9823 4113").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.MASTERCARD;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldDescriptionMastercardPaymentSystem_WhenCardNumberIsCorrectAndStarsWith55() {
        expectedResult = new CardNumberProcess("5557 6238 9823 4112").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.MASTERCARD;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldDescriptionDinersClubPaymentSystem_WhenCardNumberIsCorrectAndStarsWith36() {
        expectedResult = new CardNumberProcess("3642 1144 6253 6845").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.DINERS_CLUB;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldDescriptionDinersClubPaymentSystem_WhenCardNumberIsCorrectAndStarsWith38() {
        expectedResult = new CardNumberProcess("3842 1144 6253 6843").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.DINERS_CLUB;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldDescriptionDiscoverPaymentSystem_WhenCardNumberIsCorrectAndStarsWith6011() {
        expectedResult = new CardNumberProcess("6011 1144 6253 6841").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.DISCOVER;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldDescriptionDiscoverPaymentSystem_WhenCardNumberIsCorrectAndStarsWith65() {
        expectedResult = new CardNumberProcess("6542 1144 6253 6849").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.DISCOVER;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldDescriptionJcbPaymentSystem_WhenCardNumberIsCorrectAndStarsWith35() {
        expectedResult = new CardNumberProcess("3542 1144 6253 6846").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.JCB;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldDescriptionAmericanExpressPaymentSystem_WhenCardNumberIsCorrectAndStarsWith34() {
        expectedResult = new CardNumberProcess("3442 1144 6253 6847").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.AMERICAN_EXPRESS;

        assertEquals(expectedResult, actualResult);
    }

    @Test
    public void shouldDescriptionAmericanExpressPaymentSystem_WhenCardNumberIsCorrectAndStarsWith37() {
        expectedResult = new CardNumberProcess("3742 1144 6253 6844").operate();
        actualResult = descriptionCorrectCardNumber + PaymentSystem.AMERICAN_EXPRESS;

        assertEquals(expectedResult, actualResult);
    }

}
