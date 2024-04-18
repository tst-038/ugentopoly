package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.interfaces.Rentable;

public class RentPaidLog extends Log {
    private final Player renter;
    private final Rentable tile;

    public RentPaidLog(Player renter, Rentable tile) {
        super(String.format(PropertyReader.getInstance().get("log.property_rent_paid"), renter.getName(), Settings.getMoneyUnit()+tile.getRent(), tile.getOwner().getName()));
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
