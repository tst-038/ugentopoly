package be.ugent.objprog.ugentopoly.exceptions.data;

import be.ugent.objprog.ugentopoly.exceptions.UgentopolyException;

public class PropertyReadException extends UgentopolyException {
    public PropertyReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
