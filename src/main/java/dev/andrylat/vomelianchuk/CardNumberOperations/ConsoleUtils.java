package dev.andrylat.vomelianchuk.CardNumberOperations;

import java.util.Scanner;

public class ConsoleUtils {
    public String readCardNumber() {
        promptToTheUser();
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void promptToTheUser() {
        System.out.println("Hello! Enter card number for validation:");
    }
}
