package be.ugent.objprog.ugentopoly.model.card.factories;

import be.ugent.objprog.ugentopoly.model.card.Deck;
import be.ugent.objprog.ugentopoly.model.card.MoveRelCard;
import org.jdom2.Element;

public class MoveRelCardFactory implements CardFactory {
    @Override
    public MoveRelCard createCard(Element element, Deck deck) {
        String id = element.getAttributeValue("id");
        int relative = Integer.parseInt(element.getAttributeValue("relative"));
        return new MoveRelCard(id, relative, deck);
    }
}
