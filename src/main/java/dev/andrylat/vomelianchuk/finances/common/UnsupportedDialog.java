package dev.andrylat.vomelianchuk.finances.common;

public class UnsupportedDialog implements Dialog {
    static final String INCORRECT_ACTION_NUMBER = "The entered number does not match any action";

    @Override
    public void start() {
        System.out.println(INCORRECT_ACTION_NUMBER);
    }

    @Override
    public String getDescription() {
        throw new UnsupportedOperationException();
    }

}
