package be.ugent.objprog.ugentopoly.log.event;

import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.behaviour.IRentable;

public class RentPaidEvent extends Event {

    public RentPaidEvent(Player renter, IRentable tile) {
        super(String.format(renter.getGameManager().getPropertyreader().get("log.property_rent_paid"), renter.getName(), renter.getGameManager().getSettings().getMoneyUnit() + tile.getRent(), tile.getOwner().getName()));
    }
}
