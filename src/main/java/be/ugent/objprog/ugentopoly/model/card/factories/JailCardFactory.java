package be.ugent.objprog.ugentopoly.model.card.factories;

import be.ugent.objprog.ugentopoly.model.card.Deck;
import be.ugent.objprog.ugentopoly.model.card.JailFreeCard;
import org.jdom2.Element;

public class JailCardFactory implements CardFactory {
    @Override
    public JailFreeCard createCard(Element element, Deck deck) {
        String id = element.getAttributeValue("id");
        return new JailFreeCard(id, deck);
    }
}
