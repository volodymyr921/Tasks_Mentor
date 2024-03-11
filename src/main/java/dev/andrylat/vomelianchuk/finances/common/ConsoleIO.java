package dev.andrylat.vomelianchuk.finances.common;

public interface ConsoleIO<T> {
    T read();
    void printResult(Object result);
    void printErrors(RuntimeException e);
}
