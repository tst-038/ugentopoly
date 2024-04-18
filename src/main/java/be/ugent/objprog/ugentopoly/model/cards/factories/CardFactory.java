package be.ugent.objprog.ugentopoly.model.cards.factories;

import be.ugent.objprog.ugentopoly.model.cards.Card;
import org.jdom2.Element;

public interface CardFactory {
    Card createCard(Element element);
}
