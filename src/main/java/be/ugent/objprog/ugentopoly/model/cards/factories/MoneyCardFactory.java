package be.ugent.objprog.ugentopoly.model.cards.factories;

import be.ugent.objprog.ugentopoly.model.cards.MoneyCard;
import org.jdom2.Element;

public class MoneyCardFactory implements CardFactory {
    @Override
    public MoneyCard createCard(Element element) {
        String id = element.getAttributeValue("id");
        int amount = Integer.parseInt(element.getAttributeValue("amount"));
        return new MoneyCard(id, amount);
    }
}
