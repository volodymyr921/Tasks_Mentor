import java.util.Scanner;

/**
 * This class, UserCardNumber, is designed to obtain a card number from the user for validation.
 */
public class UserCardNumber {

    /**
     * This method prompts the user to enter a card number for validation.
     */
    public String getCardNumber() {
        promptToTheUser();
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * This method displays a message to the user asking for a card number.
     */
    public void promptToTheUser() {
        System.out.println("Hello! Enter card number for validation:");
    }
}
