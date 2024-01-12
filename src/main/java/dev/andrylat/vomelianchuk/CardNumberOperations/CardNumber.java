package dev.andrylat.vomelianchuk.CardNumberOperations;

public class CardNumber {
    public static void main(String[] args) {
        ConsoleUtils consoleUtils = new ConsoleUtils();
        String cardNumber = consoleUtils.readCardNumber();

        CardNumberProcess card = new CardNumberProcess(cardNumber);
        String resultValidation = card.operate();
        System.out.println(resultValidation);
    }
}
