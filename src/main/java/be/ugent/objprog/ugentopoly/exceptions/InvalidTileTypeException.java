package be.ugent.objprog.ugentopoly.exceptions;

public class InvalidTileTypeException extends RuntimeException {
    public InvalidTileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTileTypeException(String message) {
        super(message);
    }
}
