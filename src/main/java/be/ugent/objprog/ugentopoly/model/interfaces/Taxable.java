package be.ugent.objprog.ugentopoly.model.interfaces;

import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.TaxPaidLog;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;

public interface Taxable {
    int getAmount();

    default void payTax(Player player, Bank bank) {
        bank.transferToJackpot(player, getAmount());
        GameLogBook.getInstance().addEntry(new TaxPaidLog(player.getName(), getAmount()));
    }
}
