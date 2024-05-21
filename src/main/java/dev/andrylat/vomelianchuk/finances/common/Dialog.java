package dev.andrylat.vomelianchuk.finances.common;

import java.io.InputStream;
import java.io.PrintStream;

public interface Dialog {
    void start(InputStream inputStream, PrintStream outputStream);
    String getDescription();
}
