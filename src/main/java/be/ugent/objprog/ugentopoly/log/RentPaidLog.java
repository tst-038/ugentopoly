package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.interfaces.Rentable;

public class RentPaidLog extends Log {

    public RentPaidLog(Player renter, Rentable tile) {
        super(String.format(renter.getGame().getPropertyreader().get("log.property_rent_paid"), renter.getName(), renter.getGame().getSettings().getMoneyUnit() + tile.getRent(), tile.getOwner().getName()));
    }
}
