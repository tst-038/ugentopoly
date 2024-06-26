package be.ugent.objprog.ugentopoly.model.behaviour;

import be.ugent.objprog.ugentopoly.log.event.RentPaidEvent;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.TransactionPriority;
import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.tile.TileType;

public interface Rentable extends Ownable {
    default void payRent(Player player, GameManager gameManager) {
        if(gameManager.getBank().transfer(player, getOwner(), getRent(), TransactionPriority.MANDATORY)) {
            gameManager.getLogBook().addEntry(new RentPaidEvent(player, this));
        }
    }

    int getRent();
    TileType getType();
}
