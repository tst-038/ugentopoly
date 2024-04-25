package be.ugent.objprog.ugentopoly.log.event;

import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.behaviour.IBuyable;

public class PropertyBoughtEvent extends Event {

    public PropertyBoughtEvent(Player buyer, IBuyable property) {
        super(String.format(buyer.getGameManager().getPropertyreader().get("log.property_bought"), buyer.getName(), property.getName(), buyer.getGameManager().getSettings().getMoneyUnit() + property.getPrice()));
    }
}
