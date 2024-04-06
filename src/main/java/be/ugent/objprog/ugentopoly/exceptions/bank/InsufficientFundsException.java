package be.ugent.objprog.ugentopoly.exceptions.bank;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}