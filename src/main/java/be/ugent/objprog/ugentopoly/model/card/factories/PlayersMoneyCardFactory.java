package be.ugent.objprog.ugentopoly.model.card.factories;

import be.ugent.objprog.ugentopoly.model.card.Deck;
import be.ugent.objprog.ugentopoly.model.card.PlayersMoneyCard;
import org.jdom2.Element;

public class PlayersMoneyCardFactory implements CardFactory {
    @Override
    public PlayersMoneyCard createCard(Element element, Deck deck) {
        String id = element.getAttributeValue("id");
        int amount = Integer.parseInt(element.getAttributeValue("amount"));
        return new PlayersMoneyCard(id, amount, deck);
    }
}
