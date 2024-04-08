package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.controller.UIUpdater;
import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.PropertyBoughtLog;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;

public interface Buyable extends Ownable {
    default void buy(Player player) {
        try {
            Bank.getInstance().subtractMoney(player, getPrice());
            setOwner(player);
            UIUpdater.getInstance().playerBoughtTile(player, this);
            GameLogBook.getInstance().addEntry(new PropertyBoughtLog(player, this));
        } catch (InsufficientFundsException e) {
            e.printStackTrace();
        }
    }

    int getPrice();
}
