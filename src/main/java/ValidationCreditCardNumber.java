import java.util.ArrayList;
import java.util.List;

/**
 * The ValidationCreditCardNumber class is responsible for validating credit card numbers
 * and determining the payment system associated with the card.
 */
public class ValidationCreditCardNumber {
    // Constants defining the expected length of a credit card number
    private static final int LENGTH_CARD_NUMBER = 16;

    // Error messages for different validation scenarios
    private static final String ERROR_LENGTH_CARD = "Length should be 16 symbols";
    private static final String ERROR_ONLY_DIGITS = "Number should contain only digits";
    private static final String ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM = "Payment System can't be determined";
    private String cardNumber; // The input credit card number to be validated
    private PaymentSystem paymentSystem; // The detected payment system associated with the card
    private final List<String> errors = new ArrayList<>(); // List to hold validation errors

    /**
     * Constructor for ValidationCreditCardNumber class.
     *
     * @param cardNumber The credit card number to be validated.
     */
    public ValidationCreditCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    /**
     * Validates the provided credit card number for length, digit-only content, and determines the payment system.
     * Records any encountered errors during validation.
     */
    public void validate() {
        // Remove any white spaces from the card number
        cardNumber = cardNumber.replaceAll("\\s", "");

        // Check if the length of the card number is correct
        if (cardNumber.length() != LENGTH_CARD_NUMBER) errors.add(ERROR_LENGTH_CARD);

        // Check if the card number contains only digits
        int i = 0;
        while (i < cardNumber.length()) {
            if (Character.isLetter(cardNumber.charAt(i))) {
                errors.add(ERROR_ONLY_DIGITS);
                break;
            }
            i++;
        }

        paymentSystem = detectPaymentSystem();

        // If payment system couldn't be determined, add an error message
        if (paymentSystem == null) errors.add(ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM);

        displayTheResult();
    }

    /**
     * Detects the payment system associated with the provided card number.
     *
     * @return The PaymentSystem enum representing the detected payment system, or null if not determined.
     */
    public PaymentSystem detectPaymentSystem() {
        if (cardNumber == null) return null;
        for (PaymentSystem paymentSystem : PaymentSystem.values()) {
            for (int prefix : paymentSystem.getPrefix()) {
                if (cardNumber.startsWith(String.valueOf(prefix))) return paymentSystem;
            }
        }
        return null;
    }

    /**
     * Displays the validation result: whether the card is valid or invalid, along with any encountered errors.
     */
    public void displayTheResult() {
        if (paymentSystem != null) {
            System.out.println("\nCard is valid. Payment System is \"" + paymentSystem + "\".");
        } else {
            System.out.println("\nCard number is invalid.\n" + "Errors:");
            for (String error : errors) {
                System.out.println("-> " + error);
            }
        }
    }
}
