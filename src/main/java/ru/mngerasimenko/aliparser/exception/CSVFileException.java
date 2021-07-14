package ru.mngerasimenko.aliparser.exception;


public class CSVFileException extends Throwable {
    public CSVFileException(String message) {
        super(message);
    }

    public CSVFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVFileException(Throwable cause) {
        super(cause);
    }
}
