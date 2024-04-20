package be.ugent.objprog.ugentopoly.model.cards.factories;

import be.ugent.objprog.ugentopoly.model.cards.JailFreeCard;
import org.jdom2.Element;

public class JailCardFactory implements CardFactory {
    @Override
    public JailFreeCard createCard(Element element) {
        String id = element.getAttributeValue("id");
        return new JailFreeCard(id);
    }
}