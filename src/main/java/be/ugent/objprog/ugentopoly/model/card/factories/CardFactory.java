package be.ugent.objprog.ugentopoly.model.card.factories;

import be.ugent.objprog.ugentopoly.model.card.Card;
import be.ugent.objprog.ugentopoly.model.card.Deck;
import org.jdom2.Element;

public interface CardFactory {
    Card createCard(Element element, Deck deck);
}
