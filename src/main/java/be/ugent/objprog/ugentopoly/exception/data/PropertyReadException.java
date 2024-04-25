package be.ugent.objprog.ugentopoly.exception.data;

import be.ugent.objprog.ugentopoly.exception.UgentopolyException;

public class PropertyReadException extends UgentopolyException {
    public PropertyReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
