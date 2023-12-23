import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CreditCardValidatorTest {
    @Test
    public void testValidVisaCard() {
        assertEquals(new ValidationCreditCardNumber("4532756279624064").detectPaymentSystem(), PaymentSystem.VISA);
    }
    @Test
    public void testValidMasterCard() {
        assertEquals(new ValidationCreditCardNumber("5412345678901234").detectPaymentSystem(), PaymentSystem.MASTERCARD);
    }
    @Test
    public void testValidDinersClubCard() {
        assertEquals(new ValidationCreditCardNumber("3632756279624064").detectPaymentSystem(), PaymentSystem.DINERS_CLUB);
    }
    @Test
    public void testValidDiscoverCard() {
        assertEquals(new ValidationCreditCardNumber("6011 7562 7962 4064").detectPaymentSystem(), PaymentSystem.DISCOVER);
    }
    @Test
    public void testValidJcbCard() {
        assertEquals(new ValidationCreditCardNumber("3532756279624064").detectPaymentSystem(), PaymentSystem.JCB);
    }
    @Test
    public void testValidAmericanExpressCard() {
        assertEquals(new ValidationCreditCardNumber("3732 7562 7962 4064").detectPaymentSystem(), PaymentSystem.AMERICAN_EXPRESS);
    }

    @Test
    public void testLengthCard() {
        assertNull(new ValidationCreditCardNumber("12345823456").detectPaymentSystem());
    }
    @Test
    public void testOnlyDigitCard() {
        assertNull(new ValidationCreditCardNumber("12345s8901s3dd56").detectPaymentSystem());
    }
    @Test
    public void testNotPaymentSystemCard() {
        assertNull(new ValidationCreditCardNumber("1234567890123456").detectPaymentSystem());
    }

    @Test
    public void testEmptyCardNumber() {
        assertNull(new ValidationCreditCardNumber("").detectPaymentSystem());
    }

    @Test
    public void testNullCardNumber() {
        assertNull(new ValidationCreditCardNumber(null).detectPaymentSystem());
    }
}
