package be.ugent.objprog.ugentopoly.model.card.factories;

import be.ugent.objprog.ugentopoly.model.card.Deck;
import be.ugent.objprog.ugentopoly.model.card.MoneyCard;
import org.jdom2.Element;

public class MoneyCardFactory implements CardFactory {
    @Override
    public MoneyCard createCard(Element element, Deck deck) {
        String id = element.getAttributeValue("id");
        int amount = Integer.parseInt(element.getAttributeValue("amount"));
        return new MoneyCard(id, amount, deck);
    }
}
