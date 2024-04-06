package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;

public interface Rentable extends Ownable {
    default void payRent(Player player) {
        try {
            Bank.getInstance().transferMoney(player, getOwner(), getRent());
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        }
    }

    int getRent();
}
