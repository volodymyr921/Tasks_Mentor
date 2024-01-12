package dev.andrylat.vomelianchuk.CardNumberOperations.enums;

import java.util.Optional;

public enum PaymentSystem {
    VISA(4),
    MASTERCARD(51, 52, 53, 54, 55),
    DINERS_CLUB(36, 38),
    DISCOVER(6011, 65),
    JCB(35),
    AMERICAN_EXPRESS(34, 37);

    private final int[] prefixes;

    PaymentSystem(int... prefixes) {
        this.prefixes = prefixes;
    }

    public int[] getPrefixes() {
        return prefixes;
    }

    public static Optional<PaymentSystem> detectPaymentSystem(String cardNumber) {
        if (cardNumber == null) return Optional.empty();
        for (PaymentSystem paymentSystem : PaymentSystem.values()) {
            for (int prefix : paymentSystem.getPrefixes()) {
                if (cardNumber.startsWith(String.valueOf(prefix))) {
                    return Optional.of(paymentSystem);
                }
            }
        }
        return Optional.empty();
    }
}
