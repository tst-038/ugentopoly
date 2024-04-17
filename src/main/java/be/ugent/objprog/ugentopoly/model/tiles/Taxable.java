package be.ugent.objprog.ugentopoly.model.tiles;

import be.ugent.objprog.ugentopoly.exceptions.bank.InsufficientFundsException;
import be.ugent.objprog.ugentopoly.log.GameLogBook;
import be.ugent.objprog.ugentopoly.log.TaxPaidLog;
import be.ugent.objprog.ugentopoly.model.Bank;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.TransactionPriority;

import java.util.Optional;

public interface Taxable {
    int getAmount();
    default void payTax(Player player) {
        try {
            Bank.getInstance().transferMoney(player, Optional.empty(), getAmount(), TransactionPriority.HIGH);
            GameLogBook.getInstance().addEntry(new TaxPaidLog(player.getName(), getAmount()));
        } catch (InsufficientFundsException ignored) {
        }
    }
}
