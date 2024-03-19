package be.ugent.objprog.ugentopoly.exceptions;

public class UIInitializationException extends RuntimeException {
    public UIInitializationException(String message) {
        super(message);
    }

    public UIInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
