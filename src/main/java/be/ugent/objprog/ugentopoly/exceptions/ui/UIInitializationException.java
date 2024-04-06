package be.ugent.objprog.ugentopoly.exceptions.ui;

import be.ugent.objprog.ugentopoly.exceptions.UgentopolyException;

public class UIInitializationException extends UgentopolyException {
    public UIInitializationException(String message) {
        super(message);
    }

    public UIInitializationException(String message, Throwable cause) {
        super(message, cause);
    }
}
