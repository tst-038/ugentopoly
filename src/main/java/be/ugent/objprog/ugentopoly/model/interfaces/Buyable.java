package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.log.PropertyBoughtLog;
import be.ugent.objprog.ugentopoly.model.Player;

public interface Buyable extends Ownable {
    default void buy(Player player, Game game) {
        try {
            game.getBank().withdraw(player, getPrice());
            setOwner(player);
            player.networthProperty().set(player.networthProperty().get() + getPrice());
            game.getUIUpdater().playerBoughtTile(player, this);
            game.getLogBook().addEntry(new PropertyBoughtLog(player, this));
        } catch (InsufficientFundsException ignored) {
        }
    }

    int getPrice();
}
