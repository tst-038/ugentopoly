package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.PropertyBoughtLog;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.ui.UIUpdater;

public interface Buyable extends Ownable {
    default void buy(Player player) {
        try {
            Bank.getInstance().withdraw(player, getPrice());
            setOwner(player);
            player.setNetworth(player.getNetworth() + getPrice());
            UIUpdater.getInstance().playerBoughtTile(player, this);
            GameLogBook.getInstance().addEntry(new PropertyBoughtLog(player, this));
        } catch (InsufficientFundsException ignored) {
        }
    }

    int getPrice();
}
