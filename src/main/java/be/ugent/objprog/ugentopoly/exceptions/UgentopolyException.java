package be.ugent.objprog.ugentopoly.exceptions;

public class UgentopolyException extends RuntimeException {
    public UgentopolyException(String message, Throwable cause) {
        super(message, cause);
    }

    public UgentopolyException(String message) {
        super(message);
    }
}