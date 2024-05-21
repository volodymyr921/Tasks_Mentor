package dev.andrylat.vomelianchuk.finances.common;

import java.io.InputStream;
import java.io.PrintStream;

public class UnsupportedDialog implements Dialog {
    private static final String INCORRECT_ACTION_NUMBER = "The entered number does not match any action";
    private static final String DESCRIPTION_ERROR = "The user entered data that does not meet the requirements of the task and does not have a corresponding dialog";

    @Override
    public void start(InputStream input, PrintStream output) {
        System.out.println(INCORRECT_ACTION_NUMBER);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION_ERROR;
    }

}
