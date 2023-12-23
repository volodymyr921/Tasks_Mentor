public class CreditCardNumber {
    public static void main(String[] args) {
        String cardNumber = new UserCardNumber().getCardNumber();

        ValidationCreditCardNumber creditCard = new ValidationCreditCardNumber(cardNumber);
        creditCard.validate();
    }

}
