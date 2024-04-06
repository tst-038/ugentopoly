package be.ugent.objprog.ugentopoly.exceptions.model;

import be.ugent.objprog.ugentopoly.exceptions.UgentopolyException;

public class InvalidTileTypeException extends UgentopolyException {
    public InvalidTileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTileTypeException(String message) {
        super(message);
    }
}
