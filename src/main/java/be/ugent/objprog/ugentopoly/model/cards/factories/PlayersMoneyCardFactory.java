package be.ugent.objprog.ugentopoly.model.cards.factories;

import be.ugent.objprog.ugentopoly.model.cards.PlayersMoneyCard;
import org.jdom2.Element;

public class PlayersMoneyCardFactory implements CardFactory {
    @Override
    public PlayersMoneyCard createCard(Element element) {
        String id = element.getAttributeValue("id");
        int amount = Integer.parseInt(element.getAttributeValue("amount"));
        return new PlayersMoneyCard(id, amount);
    }
}
