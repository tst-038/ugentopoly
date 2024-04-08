package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.Settings;
import be.ugent.objprog.ugentopoly.model.interfaces.Buyable;

public class PropertyBoughtLog extends Log {

    private final Player buyer;
    private final Buyable buyableProperty;

    public PropertyBoughtLog(Player buyer, Buyable buyableProperty) {
        super(buyer.getName() + " kocht " + buyableProperty.getName() + " voor " + Settings .getMoneyUnit()+ buyableProperty.getPrice());
        this.buyer = buyer;
        this.buyableProperty = buyableProperty;
    }

    public Player getBuyer() {
        return buyer;
    }

    public Buyable getBuyableProperty() {
        return buyableProperty;
    }
}
