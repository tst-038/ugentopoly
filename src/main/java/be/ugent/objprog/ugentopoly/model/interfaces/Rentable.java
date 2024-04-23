package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.RentPaidLog;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.TransactionPriority;

public interface Rentable extends Ownable {
    default void payRent(Player player, Bank bank) {
        try {
            bank.transfer(player, getOwner(), (int) (getRent() * Math.pow(2, getOwner().getInventory().getOwnedRailways() - 1)), TransactionPriority.HIGH);
            GameLogBook.getInstance().addEntry(new RentPaidLog(player, this));
        } catch (InsufficientFundsException ignored) {
        }
    }

    int getRent();
}
