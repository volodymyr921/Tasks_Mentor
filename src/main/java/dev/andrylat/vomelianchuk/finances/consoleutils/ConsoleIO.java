package dev.andrylat.vomelianchuk.finances.consoleutils;

public interface ConsoleIO {
    Object read();
    void printResult(Object result);
    void printErrors(RuntimeException e);
}
