package com.varpa89.dnevnik.exception;

/**
 * User: varpa89
 * Date: 21.04.14
 * Time: 10:42
 */
import org.springframework.validation.Errors;

@SuppressWarnings("serial")
public class InvalidRequestException extends RuntimeException {
    private Errors errors;

    public InvalidRequestException(String message, Errors errors) {
        super(message);
        this.errors = errors;
    }

    public Errors getErrors() { return errors; }
}
