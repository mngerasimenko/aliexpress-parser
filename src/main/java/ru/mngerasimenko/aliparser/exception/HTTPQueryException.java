package ru.mngerasimenko.aliparser.exception;

public class HTTPQueryException extends Exception {
    public HTTPQueryException() {
    }

    public HTTPQueryException(String message) {
        super(message);
    }

    public HTTPQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}
