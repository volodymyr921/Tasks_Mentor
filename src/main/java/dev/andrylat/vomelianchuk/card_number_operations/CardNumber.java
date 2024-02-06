package dev.andrylat.vomelianchuk.card_number_operations;

public class CardNumber {
    public static void main(String[] args) {
            UserConsoleIO  userConsoleIO = new UserConsoleIO(System.in, System.out);
            String cardNumberAsString = userConsoleIO.readCardNumber();
            try {
                CardNumberValidator cardNumberValidator = new CardNumberValidator();
                cardNumberValidator.validate(cardNumberAsString);
                PaymentSystem paymentSystem = PaymentSystem.determine(cardNumberAsString);
                UserConsoleIO.printPaymentSystem(paymentSystem);
            } catch (CardValidationException | WrongPaymentSystemException e) {
                UserConsoleIO.printErrors(e);
            }
    }

}
