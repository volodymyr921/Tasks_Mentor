package dev.andrylat.vomelianchuk.card_number_operations.enums;

import dev.andrylat.vomelianchuk.card_number_operations.exceptions.WrongPaymentSystemException;

import java.util.Optional;

public enum PaymentSystem {
    VISA(4),
    MASTERCARD(51, 52, 53, 54, 55),
    DINERS_CLUB(36, 38),
    DISCOVER(6011, 65),
    JCB(35),
    AMERICAN_EXPRESS(34, 37);

    private static final String ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM = "Payment System can't be determine";

    private final int[] prefixes;

    PaymentSystem(int... prefixes) {
        this.prefixes = prefixes;
    }

    public int[] getPrefixes() {
        return prefixes;
    }

    public static PaymentSystem determine(String cardNumber) {
        Optional<PaymentSystem> paymentSystemOptional = PaymentSystem.of(cardNumber);
        if (paymentSystemOptional.isPresent()) {
            return paymentSystemOptional.get();
        } else {
            throw new WrongPaymentSystemException(ERROR_CANNOT_DETERMINE_PAYMENT_SYSTEM);
        }
    }

    private static Optional<PaymentSystem> of(String cardNumber) {
        if (cardNumber != null) {
            for (PaymentSystem paymentSystem : PaymentSystem.values()) {
                for (int prefix : paymentSystem.getPrefixes()) {
                    if (cardNumber.startsWith(String.valueOf(prefix))) {
                        return Optional.of(paymentSystem);
                    }
                }
            }
        }
        return Optional.empty();
    }

}
