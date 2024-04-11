package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.TransactionPriority;

import java.util.Optional;

public interface Rentable extends Ownable {
    default void payRent(Player player) {
        try {
            Bank.getInstance().transferMoney(player, Optional.of(getOwner()), (int) (getRent()*Math.pow(2, player.getOwnedRailways())), TransactionPriority.HIGH);
        } catch (InsufficientFundsException ignored) {
        }
    }

    int getRent();
}
