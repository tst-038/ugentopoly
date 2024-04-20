package be.ugent.objprog.ugentopoly.model.cards.factories;

import be.ugent.objprog.ugentopoly.model.cards.Deck;
import be.ugent.objprog.ugentopoly.model.cards.MoveCard;
import org.jdom2.Element;

public class MoveCardFactory implements CardFactory {
    @Override
    public MoveCard createCard(Element element, Deck deck) {
        String id = element.getAttributeValue("id");
        int position = Integer.parseInt(element.getAttributeValue("position"));
        boolean collect = Boolean.parseBoolean(element.getAttributeValue("collect"));
        return new MoveCard(id, position, collect, deck);
    }
}
