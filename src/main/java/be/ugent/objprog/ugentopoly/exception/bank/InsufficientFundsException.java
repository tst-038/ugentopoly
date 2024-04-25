package be.ugent.objprog.ugentopoly.exception.bank;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}