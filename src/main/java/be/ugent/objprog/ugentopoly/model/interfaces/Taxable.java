package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.controller.Game;
import be.ugent.objprog.ugentopoly.log.TaxPaidLog;
import be.ugent.objprog.ugentopoly.model.Player;

public interface Taxable {
    int getAmount();

    default void payTax(Player player, Game game) {
        game.getBank().transferToJackpot(player, getAmount());
        game.getLogBook().addEntry(new TaxPaidLog(player, getAmount()));
    }
}
