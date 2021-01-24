package com.ws.brainfuck.exception;

/**
 * Exception is thrown in case of invalid input data.
 * The class {@code ParseException} and its subclasses are a form of
 * {@code Exception} that indicates conditions that a reasonable
 * application might want to catch. The class {@code Exception} and any
 * subclasses are checked exceptions. Checked exceptions need to be
 * declared in a method or constructor's {@code throws} clause if they
 * can be thrown by the execution of the method or constructor and
 * propagate outside the method or constructor boundary.
 */
public class ParseException extends RuntimeException {

    public ParseException(String message) {
        super(message);
    }
}
