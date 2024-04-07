package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.interfaces.Rentable;

public class RentPaidLog extends Log {
    private final Player renter;
    private final Rentable tile;

    public RentPaidLog(Player renter, Rentable tile) {
        super(renter.getName() + " paid " +tile.getRent() + " rent to " + tile.getOwner().getName());
        this.renter = renter;
        this.tile = tile;
    }

    public Player getRenter() {
        return renter;
    }

    public Rentable getTile() {
        return tile;
    }
}
