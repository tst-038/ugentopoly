package be.ugent.objprog.ugentopoly.model.behaviour;

import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.log.event.TaxPaidEvent;
import be.ugent.objprog.ugentopoly.model.player.Player;

public interface ITaxable {
    int getAmount();

    default void payTax(Player player, GameManager gameManager) {
        gameManager.getBank().transferToJackpot(player, getAmount());
        gameManager.getLogBook().addEntry(new TaxPaidEvent(player, getAmount()));
    }
}
