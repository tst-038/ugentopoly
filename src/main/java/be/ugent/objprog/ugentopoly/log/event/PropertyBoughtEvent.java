package be.ugent.objprog.ugentopoly.log.event;

import be.ugent.objprog.ugentopoly.model.behaviour.Buyable;
import be.ugent.objprog.ugentopoly.model.player.Player;

public class PropertyBoughtEvent extends Event {

    public PropertyBoughtEvent(Player buyer, Buyable property) {
        super(String.format(buyer.getGameManager().getPropertyreader().get("log.property_bought"), buyer.getName(), property.getName(), buyer.getGameManager().getSettings().getMoneyUnit() + property.getPrice()));
    }
}
