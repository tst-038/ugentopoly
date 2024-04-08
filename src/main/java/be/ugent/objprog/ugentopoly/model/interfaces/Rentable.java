package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.TransactionPriority;

public interface Rentable extends Ownable {
    default void payRent(Player player) {
        try {
            Bank.getInstance().transferMoney(player, getOwner(), getRent(), TransactionPriority.HIGH);
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        }
    }

    int getRent();
}
