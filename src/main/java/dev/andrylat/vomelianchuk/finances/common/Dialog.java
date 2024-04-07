package dev.andrylat.vomelianchuk.finances.common;

import java.io.InputStream;
import java.io.PrintStream;

public interface Dialog {
    InputStream input = System.in;
    PrintStream output = System.out;
    void start();
    String getDescription();
}
