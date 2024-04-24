package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.model.Player;
import be.ugent.objprog.ugentopoly.model.cards.Card;

public class CardDrawnLog extends Log {

    public CardDrawnLog(Player player, Card card) {
        super(String.format(player.getGame().getPropertyreader().get("log.chance"), player.getName(), card.getDescription()));
    }
}
