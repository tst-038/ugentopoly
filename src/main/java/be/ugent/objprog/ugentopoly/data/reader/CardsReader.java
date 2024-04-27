package be.ugent.objprog.ugentopoly.data.reader;

import be.ugent.objprog.ugentopoly.data.ResourceLoader;
import be.ugent.objprog.ugentopoly.exception.data.AreaReadException;
import be.ugent.objprog.ugentopoly.logic.GameManager;
import be.ugent.objprog.ugentopoly.model.card.Card;
import be.ugent.objprog.ugentopoly.model.card.CardType;
import be.ugent.objprog.ugentopoly.model.card.Deck;
import be.ugent.objprog.ugentopoly.model.card.factories.*;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.Map;

public class CardsReader implements XmlReader {
    private final GameManager gameManager;

    public CardsReader(GameManager gameManager) {
        this.gameManager = gameManager;
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
        return deckType.equals("CHANCE") ? gameManager.getDeckManager().getChanceDeck() : gameManager.getDeckManager().getCommunityDeck();
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