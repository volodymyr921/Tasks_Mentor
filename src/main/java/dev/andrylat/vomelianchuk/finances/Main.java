package dev.andrylat.vomelianchuk.finances;

import dev.andrylat.vomelianchuk.finances.dialog.CardNumberValidatorDialog;
import dev.andrylat.vomelianchuk.finances.dialog.Dialog;
import dev.andrylat.vomelianchuk.finances.dialog.MortgageCalculatorDialog;
import dev.andrylat.vomelianchuk.finances.dialog.UnsupportedDialog;

import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    static final String INITIAL_MESSAGE = "Enter the action number:";
    static final String TEXT_ERROR_DATA_IS_NOT_NUMBER = "The data entered is not a number!";

    static final  Map<Integer, Dialog> listDialogs = Map.of(
            1, new CardNumberValidatorDialog(),
            2, new MortgageCalculatorDialog()
    );
    static final Dialog unsupportedDialog = new UnsupportedDialog();

    public static void main(String[] args) {
        try {
            int choiceUser = getChoiceUser();
            Dialog dialog = listDialogs.getOrDefault(choiceUser, unsupportedDialog);
            dialog.start();
        } catch (InputMismatchException e) {
            System.out.println(TEXT_ERROR_DATA_IS_NOT_NUMBER);
        }
    }

    private static int getChoiceUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.println(INITIAL_MESSAGE);
        for (int i = 1; i <= listDialogs.size(); i++) {
            System.out.println(listDialogs.get(i).getDescription());
        }

        return scanner.nextInt();
    }

}
