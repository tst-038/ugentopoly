package be.ugent.objprog.ugentopoly.model.behaviour;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.log.event.PropertyBoughtEvent;
import be.ugent.objprog.ugentopoly.model.player.Player;

public interface IBuyable extends IOwnable {
    default void buy(Player player, GameManager gameManager) {
        if(gameManager.getBank().withdraw(player, getPrice())) {
            setOwner(player);
            player.networthProperty().set(player.networthProperty().get() + getPrice());
            gameManager.getUIUpdater().playerBoughtTile(player, this);
            gameManager.getLogBook().addEntry(new PropertyBoughtEvent(player, this));
        }
    }

    int getPrice();
}
