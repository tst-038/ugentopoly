package be.ugent.objprog.ugentopoly.log.event;

import be.ugent.objprog.ugentopoly.model.player.Player;
import be.ugent.objprog.ugentopoly.model.card.Card;

public class CardDrawnEvent extends Event {

    public CardDrawnEvent(Player player, Card card) {
        super(String.format(player.getGameManager().getPropertyreader().get("log.chance"), player.getName(), card.getDescription()));
    }
}
