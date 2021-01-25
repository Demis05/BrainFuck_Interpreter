package com.ws.brainfuck.exception;

/**
 * ParseException is thrown in case of invalid input data.
 * The class {@code ParseException} and its subclasses are a form of
 * {@code RuntimeException}. RuntimeException is the superclass
 * of those exceptions that can be thrown during the normal operation of
 * the Java Virtual Machine. RuntimeException and its subclasses are unchecked
 * exceptions. Unchecked exceptions do not need to be declared in a method or
 * constructor's throws clause if they can be thrown by the execution of the
 * method or constructor and propagate outside the method or constructor boundary.
 */
public class ParseException extends RuntimeException {

    public ParseException(String message) {
        super(message);
    }
}
