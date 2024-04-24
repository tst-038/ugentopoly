package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.model.Player;

public class TaxPaidLog extends Log {

    public TaxPaidLog(Player player, int amount) {
        super(String.format(player.getGame().getPropertyreader().get("log.tax_paid"), player, player.getGame().getSettings().getMoneyUnit() + amount));
    }
}
