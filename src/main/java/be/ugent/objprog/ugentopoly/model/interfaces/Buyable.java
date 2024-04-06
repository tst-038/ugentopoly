package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;

public interface Buyable extends Ownable {
    default void buy(Player player) {
        try {
            Bank.getInstance().subtractMoney(player, getPrice());
            setOwner(player);
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        }
    }

    int getPrice();
}
