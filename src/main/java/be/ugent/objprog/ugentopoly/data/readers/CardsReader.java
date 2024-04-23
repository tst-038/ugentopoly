package be.ugent.objprog.ugentopoly.data.readers;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.exceptions.data.AreaReadException;
import be.ugent.objprog.ugentopoly.model.cards.Card;
import be.ugent.objprog.ugentopoly.model.cards.CardType;
import be.ugent.objprog.ugentopoly.model.cards.Deck;
import be.ugent.objprog.ugentopoly.model.cards.factories.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

public class CardsReader implements XmlReader {
    private static CardsReader instance;

    private CardsReader() {
        // Private constructor to hide the implicit one
    }

    public static CardsReader getInstance() {
        if (instance == null) {
            instance = new CardsReader();
        }
        return instance;
    }

    public void readCards() {
        try (InputStream xmlInputStream = ResourceLoader.loadResource("ugentopoly.xml")) {
            Document document = parseXml(xmlInputStream);
            Element root = getRootElement(document);

            for (Element deckElement : root.getChildren("deck")) {
                Deck deck = getDeckFromElement(deckElement);
                parseCardsForDeck(deckElement, deck);
                deck.shuffle();
            }
        } catch (IOException | JDOMException e) {
            throw new AreaReadException("Failed to read cards information from XML file", e);
        }
    }

    private Deck getDeckFromElement(Element deckElement) {
        String deckType = deckElement.getAttributeValue("type");
        return deckType.equals("CHANCE") ? Deck.getChanceDeck() : Deck.getCommunityChestDeck();
    }

    private void parseCardsForDeck(Element deckElement, Deck deck) {
        Map<CardType, CardFactory> cardFactories = createCardFactories();
        for (Element cardElement : deckElement.getChildren("card")) {
            CardType cardType = CardType.valueOf(cardElement.getAttributeValue("type"));
            CardFactory cardFactory = cardFactories.get(cardType);

            if (cardFactory == null) {
                throw new IllegalArgumentException("Unknown card type: " + cardType);
            }

            Card card = cardFactory.createCard(cardElement, deck);
            deck.addCard(card);
        }
    }

    private Map<CardType, CardFactory> createCardFactories() {
        Map<CardType, CardFactory> cardFactories = new EnumMap<>(CardType.class);
        cardFactories.put(CardType.JAIL, new JailCardFactory());
        cardFactories.put(CardType.MOVE, new MoveCardFactory());
        cardFactories.put(CardType.MONEY, new MoneyCardFactory());
        cardFactories.put(CardType.PLAYERS_MONEY, new PlayersMoneyCardFactory());
        cardFactories.put(CardType.MOVEREL, new MoveRelCardFactory());
        return cardFactories;
    }
}