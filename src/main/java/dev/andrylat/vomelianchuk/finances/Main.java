package dev.andrylat.vomelianchuk.finances;

import dev.andrylat.vomelianchuk.finances.cardnumbervalidator.dialog.CardNumberValidatorDialog;
import dev.andrylat.vomelianchuk.finances.common.Dialog;
import dev.andrylat.vomelianchuk.finances.common.UnsupportedDialog;
import dev.andrylat.vomelianchuk.finances.mortgagecalculator.dialog.MortgageCalculatorDialog;

import java.util.Map;
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static final String INITIAL_MESSAGE = "Enter the action number:";
    private static final String SEPARATOR = " - ";
    private static final String TEXT_ERROR_DATA_IS_NOT_NUMBER = "The data entered is not a number!";

    private static final Map<Integer, Dialog> listDialogs = Map.of(
            1, new CardNumberValidatorDialog(),
            2, new MortgageCalculatorDialog()
    );
    private static final Dialog unsupportedDialog = new UnsupportedDialog();

    public static void main(String[] args) {
        try {
            var choiceUser = getChoiceUser();
            var dialog = listDialogs.getOrDefault(choiceUser, unsupportedDialog);
            dialog.start();
        } catch (InputMismatchException e) {
            System.out.println(TEXT_ERROR_DATA_IS_NOT_NUMBER);
        }
    }

    private static int getChoiceUser() {
        var scanner = new Scanner(System.in);
        System.out.println(INITIAL_MESSAGE);
        for (int i = 1; i <= listDialogs.size(); i++) {
            System.out.println(i + SEPARATOR + listDialogs.get(i).getDescription());
        }

        return scanner.nextInt();
    }

}
