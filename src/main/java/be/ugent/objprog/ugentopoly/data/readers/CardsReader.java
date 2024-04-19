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

public class CardsReader {

    public void readCards() {
        try (InputStream xmlInputStream = ResourceLoader.loadResource("ugentopoly.xml")) {
            SAXBuilder builder = new SAXBuilder();
            Document document = builder.build(xmlInputStream);
            Element root = document.getRootElement();

            Map<CardType, CardFactory> cardFactories = createCardFactories();

            for (Element deckElement : root.getChildren("deck")) {
                String deckType = deckElement.getAttributeValue("type");
                Deck deck = deckType.equals("CHANCE") ? Deck.getChanceDeck() : Deck.getCommunityChestDeck();

                for (Element cardElement : deckElement.getChildren("card")) {
                    CardType cardType = CardType.valueOf(cardElement.getAttributeValue("type"));
                    CardFactory cardFactory = cardFactories.get(cardType);
                    if (cardFactory == null) {
                        throw new IllegalArgumentException("Unknown card type: " + cardType);
                    }

                    Card card = cardFactory.createCard(cardElement);
                    deck.addCard(card);
                }

                deck.shuffle();
            }
        } catch (IOException | JDOMException e) {
            throw new AreaReadException("Failed to read cards information from XML file", e);
        }
    }

    // TODO remove out of method into variable
    // + in other readers same
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