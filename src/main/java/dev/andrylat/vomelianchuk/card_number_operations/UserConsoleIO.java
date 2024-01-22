package dev.andrylat.vomelianchuk.card_number_operations;

import java.util.Scanner;

public class UserConsoleIO {
    public String readCardNumber() {
        promptToTheUser();
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    private void promptToTheUser() {
        System.out.println("Hello! Enter card number for validation:");
    }

}
