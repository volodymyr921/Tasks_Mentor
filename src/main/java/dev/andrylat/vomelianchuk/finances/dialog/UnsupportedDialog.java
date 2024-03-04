package dev.andrylat.vomelianchuk.finances.dialog;

public class UnsupportedDialog implements Dialog {
    static final String INCORRECT_ACTION_NUMBER = "The entered number does not match any action";
    static final String DESCRIPTION_ACTION = "Unsupported Dialog";

    @Override
    public void start() {
        System.out.println(INCORRECT_ACTION_NUMBER);
    }

    @Override
    public String getDescription() {
        return DESCRIPTION_ACTION;
    }

}
