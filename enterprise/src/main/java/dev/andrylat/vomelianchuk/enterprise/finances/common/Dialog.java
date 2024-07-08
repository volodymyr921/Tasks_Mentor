package dev.andrylat.vomelianchuk.enterprise.finances.common;

import java.io.InputStream;
import java.io.PrintStream;

public interface Dialog {
    void start(InputStream inputStream, PrintStream outputStream);
    String getDescription();
}
