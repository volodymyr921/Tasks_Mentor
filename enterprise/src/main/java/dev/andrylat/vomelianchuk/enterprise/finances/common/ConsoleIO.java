package dev.andrylat.vomelianchuk.enterprise.finances.common;

public interface ConsoleIO<T> {
    T read();
    void printResult(Object result);
    void printErrors(RuntimeException e);
}
