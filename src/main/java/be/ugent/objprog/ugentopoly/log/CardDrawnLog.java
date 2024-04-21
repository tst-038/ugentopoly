package be.ugent.objprog.ugentopoly.log;

import be.ugent.objprog.ugentopoly.data.readers.PropertyReader;
import be.ugent.objprog.ugentopoly.model.cards.Card;

public class CardDrawnLog extends Log {

    public CardDrawnLog(String playerName, Card card) {
        super(String.format(PropertyReader.getInstance().get("log.chance"), playerName, card.getDescription()));
    }
}
