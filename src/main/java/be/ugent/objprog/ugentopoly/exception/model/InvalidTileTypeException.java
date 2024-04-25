package be.ugent.objprog.ugentopoly.exception.model;

import be.ugent.objprog.ugentopoly.exception.UgentopolyException;

public class InvalidTileTypeException extends UgentopolyException {
    public InvalidTileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTileTypeException(String message) {
        super(message);
    }
}
