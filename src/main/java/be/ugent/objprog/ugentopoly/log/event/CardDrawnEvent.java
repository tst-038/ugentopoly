package be.ugent.objprog.ugentopoly.log.event;

import be.ugent.objprog.ugentopoly.model.card.Card;
import be.ugent.objprog.ugentopoly.model.player.Player;

public class CardDrawnEvent extends Event {

    public CardDrawnEvent(Player player, Card card) {
        super(String.format(player.getGameManager().getPropertyreader().get("log.cardDrawn"), player.getName(), card.getDescription()));
    }
}
