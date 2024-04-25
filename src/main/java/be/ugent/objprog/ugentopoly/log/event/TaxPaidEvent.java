package be.ugent.objprog.ugentopoly.log.event;

import be.ugent.objprog.ugentopoly.model.player.Player;

public class TaxPaidEvent extends Event {

    public TaxPaidEvent(Player player, int amount) {
        super(String.format(player.getGameManager().getPropertyreader().get("log.tax_paid"), player.getName(), player.getGameManager().getSettings().getMoneyUnit() + amount));
    }
}
